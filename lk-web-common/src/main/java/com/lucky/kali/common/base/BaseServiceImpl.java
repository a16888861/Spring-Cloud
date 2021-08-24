package com.lucky.kali.common.base;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.lucky.kali.common.exception.BaseException;
import com.lucky.kali.common.util.BeanUtil;
import com.lucky.kali.common.util.UUIDUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * serviceImpl实现
 */
public class BaseServiceImpl<M extends BaseMapper<E>, E extends BaseEntity, D extends BaseDTO> implements BaseService<E, D> {

    protected Log log = LogFactory.getLog(getClass());

    @Autowired
    protected M mapper;

//    @Resource
//    private SqlSessionTemplate sqlSessionTemplate;

    protected Class<T> mapperClass = currentMapperClass();

    protected Class<T> entityClass = currentModelClass();

    protected Class<T> currentMapperClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(),getClass(), 0);
    }

    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(),getClass(), 1);
    }

    @Override
    public int insert(D dto) {
        dto.setDelFlag(BaseDTO.DEL_FLAG_NORMAL);
        dto.setCreateDate(LocalDateTime.now());
        dto.setId(UUIDUtils.getInstance().generateShortUuid());
        E entity = transFromD(dto);
        return mapper.insert(entity);
    }

    @Override
    public int deleteById(Serializable id) {
        return mapper.deleteById(id);
    }

    @Override
    public int deleteByMap(Map<String, Object> columnMap) {
        return mapper.deleteByMap(columnMap);
    }

    @Override
    public int delete(Wrapper<E> queryWrapper) {
        return mapper.delete(queryWrapper);
    }

    @Override
    public int deleteBatchIds(Collection<? extends Serializable> idList) {
        return mapper.deleteBatchIds(idList);
    }

    @Override
    public int updateById(D dto) {
        E entity = transFromD(dto);
        BeanUtil.copyProperties(entity, dto);
        return mapper.updateById(entity);
    }

    @Override
    public int update(D dto, Wrapper<E> updateWrapper) {
        E entity = transFromD(dto);
        return mapper.update(entity, updateWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateBatchById(Collection<D> dtoList, int batchSize) {
        String sqlStatement = getSqlStatement(SqlMethod.UPDATE_BY_ID);
        return executeBatch(dtoList, batchSize, (sqlSession, dto) -> {
            E entity = transFromD(dto);
            MapperMethod.ParamMap<E> param = new MapperMethod.ParamMap<>();
            param.put(Constants.ENTITY, entity);
            sqlSession.update(sqlStatement, param);
        });
    }

    /**
     * 获取mapperStatementId
     *
     * @param sqlMethod 方法名
     * @return 命名id
     * @since 3.4.0
     */
    protected String getSqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.getSqlStatement(mapperClass, sqlMethod);
    }

    /**
     * 执行批量操作
     *
     * @param list      数据集合
     * @param batchSize 批量大小
     * @param consumer  执行方法
     * @param <E>       泛型
     * @return 操作结果
     * @since 3.3.1
     */
    protected <E> boolean executeBatch(Collection<E> list, int batchSize, BiConsumer<SqlSession, E> consumer) {
        return SqlHelper.executeBatch(this.entityClass, this.log, list, batchSize, consumer);
    }

    @Override
    public D selectById(Serializable id) {
        E entity = mapper.selectById(id);
        return entity==null? null: (D) entity.transDTO();
    }

    @Override
    public List<D> selectBatchIds(Collection<? extends Serializable> idList) {
        return mapper.selectBatchIds(idList).stream().<D>map(entity -> (D) entity.transDTO()).collect(Collectors.toList());
    }

    @Override
    public List<D> selectByMap(Map<String, Object> columnMap) {
        return mapper.selectByMap(columnMap).stream().<D>map(entity -> (D) entity.transDTO()).collect(Collectors.toList());
    }

    @Override
    public D selectOne(Wrapper<E> queryWrapper) {
        E entity = mapper.selectOne(queryWrapper);
        return entity == null ? null : (D) entity.transDTO();
    }

    @Override
    public Long selectCount(Wrapper<E> queryWrapper) {
        return mapper.selectCount(queryWrapper);
    }

    @Override
    public List<D> selectList(Wrapper<E> queryWrapper) {
        return mapper.selectList(queryWrapper).stream().<D>map(entity -> (D) entity.transDTO()).collect(Collectors.toList());
    }

    @Override
    public List<D> selectListAll() {
        return mapper.selectList(null).stream().<D>map(entity -> (D) entity.transDTO()).collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> selectMaps(Wrapper<E> queryWrapper) {
        return mapper.selectMaps(queryWrapper);
    }

    @Override
    public List<Object> selectObjs(Wrapper<E> queryWrapper) {
        return mapper.selectObjs(queryWrapper);
    }

    @Override
    public <P extends IPage<D>> P selectPage(P page, Wrapper<E> queryWrapper) {
        Page<E> ePage = new Page<>();
        ePage.setCurrent(page.getCurrent());
        ePage.setSize(page.getSize());
        ePage.setOrders(page.orders());
        IPage<E> eiPage = mapper.selectPage(ePage, queryWrapper);
        page.setRecords(eiPage.getRecords().stream().<D>map(entity -> (D) entity.transDTO()).collect(Collectors.toList()));
        page.setPages(eiPage.getPages());
        page.setTotal(eiPage.getTotal());
        return page;
    }

    @Override
    public <P extends IPage<Map<String, Object>>> P selectMapsPage(P page, Wrapper<E> queryWrapper) {
        return mapper.selectMapsPage(page, queryWrapper);
    }

    private E transFromD(D dto) {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        Class<E> clazz = (Class<E>) pt.getActualTypeArguments()[1];
        // 通过反射创建entity的实例
        try {
            E entity = clazz.newInstance();
            return (E) entity.recDTO(dto);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new BaseException("entity 实例化出错");
        }
    }

//    @Override
//    @Transactional(rollbackFor = Exception.class)
//    public boolean saveBatch(Collection<D> dtoList) {
//        if(CollectionUtils.isEmpty(dtoList)){
//            return true;
//        }
//        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
//        Class<E> clazz = (Class<E>) pt.getActualTypeArguments()[0];
//
//        try {
//            //通过整合包提供的Builder，构造MyBatisBatchItemWriter
//            MyBatisBatchItemWriter<E> itemWriter = new MyBatisBatchItemWriterBuilder<E>()
//                    .sqlSessionTemplate(new SqlSessionTemplate(sqlSessionTemplate.getSqlSessionFactory(), ExecutorType.BATCH))
//                    .statementId(clazz.getName() + ".insert")
//                    .build();
//
//            //对构造出对itemWriter，进行了校验
//            itemWriter.afterPropertiesSet();
//
//            itemWriter.write(dtoList.stream().map(dto -> {
//                E entity = transFromD(dto);
//                entity.setId(UUIDUtils.getInstance().generateShortUuid());
//                BeanUtil.copyProperties(entity, dto);
//                return entity;
//            }).collect(Collectors.toList()));
//            return true;
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return false;
//        // 实现方式2 ：该实现 在错误的情况下不能回滚
////        SqlSession sqlSession = null;
////        try{
////            sqlSession = SqlSessionUtils.getSqlSession(
////                    sqlSessionTemplate.getSqlSessionFactory(),
////                    ExecutorType.BATCH,sqlSessionTemplate.getPersistenceExceptionTranslator());
////            dtoList.stream().forEach(dto ->{
////                E entity = transFromD(dto);
////                entity.setId(UUIDUtils.getInstance().generateShortUuid());
////                dto.setId(entity.getId());
////                mapper.insert(transFromD(dto));
////            });
////
////            sqlSession.commit();
////            sqlSession.close();
////        }catch (Exception e){
////            e.printStackTrace();
////            sqlSession.rollback();
////            throw new BaseException("批量插入出错");
////        }finally {
////            if(sqlSession!=null){
////                sqlSession.close();
////            }
////        }
//    }

    /**
     * 集合根据属性去重
     * @return  结果
     */
    @Override
    public <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> concurrentHashMap = new ConcurrentHashMap<>();
        return t -> concurrentHashMap.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
