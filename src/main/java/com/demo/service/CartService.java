package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.dto.CartRequest;
import com.demo.entity.Cart;
import com.demo.entity.Course;
import com.demo.entity.User;
import com.demo.repository.CartRepository;
import com.demo.repository.CourseRepository;
import com.demo.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Cart addToCart(CartRequest cartRequest) {
        User user = userRepository.findById(cartRequest.getUserId()).orElse(null);
        Course course = courseRepository.findById(cartRequest.getCourseId()).orElse(null);

        if (user != null && course != null) {
            Cart cartItem = new Cart();
            cartItem.setUser(user);
            cartItem.setCourse(course);
            return cartRepository.save(cartItem);
        }
        return null;
    }

    public void removeFromCart(Long id) {
        cartRepository.deleteById(id);
    }
}
