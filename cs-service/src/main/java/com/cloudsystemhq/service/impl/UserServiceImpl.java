package com.cloudsystemhq.service.impl;

import com.cloudsystemhq.model.domain.User;
import com.cloudsystemhq.repository.UserRepository;
import com.cloudsystemhq.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(final User admin) {
        Assert.notNull(admin, "Admin is null.");
        return userRepository.save(admin);
    }

    @Override
    public Optional<User> findOne(final Long id) {
        Assert.notNull(id, "Admin id is null.");
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> findPage(final Integer page, final Integer size) {
        Assert.notNull(page, "Page number is null.");
        Assert.notNull(size, "Page size is null.");
        return userRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    @Transactional
    public Optional<User> update(final Long id, final User user) {
        Assert.notNull(id, "User id is null.");
        Assert.notNull(user, "User is null.");
        return userRepository.findById(id).map(updateUser(user));
    }

    @Override
    @Transactional
    public Optional<User> delete(final Long id) {
        Assert.notNull(id, "User id is null.");
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresent(userRepository::delete);
        return userOptional;
    }

    private Function<User, User> updateUser(final User updatedUser) {
        return (persistedUser) -> {
            persistedUser.setEmail(updatedUser.getEmail());
            persistedUser.setName(updatedUser.getName());
            persistedUser.setPhone(updatedUser.getPhone());
            return persistedUser; };
    }
}
