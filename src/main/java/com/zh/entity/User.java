package com.zh.entity;


import lombok.Data;
import org.bson.types.ObjectId;

/**
 * @author Hxx
 */
@Data
public class User {

    public String _id;

    public String userId;

    public String userName;

    public String userPw;

    public String userPwNew;

    public String userPwRepeat;

}


    