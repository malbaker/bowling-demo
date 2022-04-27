package com.example.schedubowl.controller;

import com.example.schedubowl.entities.Reservation;
import com.example.schedubowl.entities.User;
import com.example.schedubowl.services.CustomUserDetailsService;
import com.example.schedubowl.services.ReservationServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ReservationController {

    private CustomUserDetailsService userService;
    private ReservationServiceImpl reservationService;

    @RequestMapping(value = "/view_res", method = RequestMethod.GET)
    public ModelAndView reservation(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Reservation> resList = reservationService.userReservations(user.getEmail());
        modelAndView.addObject("userReservations");
        modelAndView.setViewName("view_res");
        return modelAndView;
    }
}