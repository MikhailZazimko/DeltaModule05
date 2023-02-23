package com.javarush.spring07.repository;

import com.javarush.spring07.entity.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;


@SuppressWarnings("NullableProblems")
public interface Repo extends JpaRepository<User, Long> {


    Page<User> findAllBy(Pageable pageable);

    List<User> findAllBy(Sort sort);

    @Query("select u from User u where u.login " +
            "like concat(:startWith, '%') " +
            "and u.login like concat('%', :endingWith)")
    List<User> findUserByLogin(@Param("startWith") String startWith, @Param("endingWith") String endingWith);

    @Override
    List<User> findAll();

    @Override
    List<User> findAll(Sort sort);

    @Query("select u from User u where u.id = :longs")
    @Override
    List<User> findAllById(@Param("longs") Iterable<Long> longs);

    @Override
    <S extends User> List<S> saveAll(Iterable<S> entities);

    @Override
    void flush();

    @Override
    <S extends User> S saveAndFlush(S entity);

    @Override
    <S extends User> List<S> saveAllAndFlush(Iterable<S> entities);

    @Override
    void deleteAllInBatch(Iterable<User> entities);

    @Override
    void deleteAllByIdInBatch(Iterable<Long> longs);

    @Override
    void deleteAllInBatch();

    @Override
    User getOne(Long aLong);

    @Override
    User getById(Long aLong);

    @Query("select u from User u where u.id = :aLong")
    @Override
    User getReferenceById(@Param("aLong") Long aLong);

    @Override
    <S extends User> List<S> findAll(Example<S> example);

    @Override
    <S extends User> List<S> findAll(Example<S> example, Sort sort);

    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    <S extends User> S save(S entity);

    @Override
    Optional<User> findById(Long aLong);

    @Override
    boolean existsById(Long aLong);

    @Override
    long count();

    @Override
    void deleteById(Long aLong);

    @Override
    void delete(User entity);

    @Override
    void deleteAllById(Iterable<? extends Long> longs);

    @Override
    void deleteAll(Iterable<? extends User> entities);

    @Override
    void deleteAll();

    @Override
    <S extends User> Optional<S> findOne(Example<S> example);

    @Override
    <S extends User> Page<S> findAll(Example<S> example, Pageable pageable);

    @Override
    <S extends User> long count(Example<S> example);

    @Override
    <S extends User> boolean exists(Example<S> example);

    @Override
    <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction);
}
