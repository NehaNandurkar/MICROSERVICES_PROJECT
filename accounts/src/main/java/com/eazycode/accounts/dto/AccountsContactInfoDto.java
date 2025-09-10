package com.eazycode.accounts.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

//There won't be any setter methods , java will automatically create the constructor and getter methods to read application.properties 
@ConfigurationProperties(prefix="accounts")
public record AccountsContactInfoDto(String message,Map<String,String> contactDetails,List<String> onCallSupport) {

}
