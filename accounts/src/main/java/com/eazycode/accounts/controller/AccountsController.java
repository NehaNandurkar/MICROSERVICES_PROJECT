package com.eazycode.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eazycode.accounts.constants.AccountsConstants;
import com.eazycode.accounts.dto.CustomerDto;
import com.eazycode.accounts.dto.ErrorResponseDto;
import com.eazycode.accounts.dto.ResponseDto;
import com.eazycode.accounts.service.IAccountsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

//@Tag Provides metadata for Swagger UI common to all REST API 
@Tag(
		name="CRUD REST API for Accounts in EazyCode",
		description="CRUD REST APIs in EazyBank to CREATE, UPDATE, FETCH AND DELETE account details")

@RestController
@RequestMapping(path="/api")
@Validated
/**
 *You put @NotNull, @Email, etc. on entity/DTO fields → these are just rules written on the class.
 *But rules don’t run automatically.
 *@Validated (or @Valid) on the controller tells Spring: “Hey, before this request goes into the method, actually apply those rules and throw an error if they fail.”
 *@Valid Works only for basic bean validation annotations (@NotNull, @Email, @Size, etc.).
 *@Validated Does everything @Valid does + adds support for validation groups (advanced feature where you can validate differently in different scenarios).
 */
public class AccountsController {
	
	@Autowired
	private IAccountsService iAccountsService;
	
	@Value("${build.version}")//Taking the value from application.properties file 
	private String buildVersion;
	
	@Autowired
	private Environment environment;
	
	//@Operation Provides metadata for Swagger UI for CREATE API
	 @Operation(
	            summary = "Create Account REST API",
	            description = "REST API to create new Customer &  Account inside EazyBank"
	    )
	 @ApiResponses({
         @ApiResponse(
                 responseCode = "201",
                 description = "HTTP Status CREATED"
         ),
         @ApiResponse(
                 responseCode = "500",
                 description = "HTTP Status Internal Server Error",
                 content = @Content(
                         schema = @Schema(implementation = ErrorResponseDto.class)
                         //Telling the spring doc whenever the 500 error happen I am going to send the ErrorResponse by following the schema defined inside this ErrorResponseDto class. 
                         //Now the ErrorResponseDto will be visible in Swagger UI which was previously not visible becoz we are throwing the ErrorResponseDto only from our GlobalException logic.And openAPI doc cannot scan GlobalException automatically.
                 )
         )
 }
 )
	@PostMapping("/create")
	public ResponseEntity<ResponseDto>createAccountForCustomer(@Valid @RequestBody CustomerDto customerDto){
		iAccountsService.createAccountForCustomer(customerDto);
		return  ResponseEntity
				.status(HttpStatus.CREATED)
				.body(new ResponseDto(AccountsConstants.MESSAGE_201,AccountsConstants.MESSAGE_201));
               
    } 
	
	@Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
    )
	@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
}
)
	@GetMapping("/fetch")
	public ResponseEntity<CustomerDto>fetchAccountDetails(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message="Mobile number must be 10 digits") String mobileNumber){
		CustomerDto customerDto=iAccountsService.fetchAccountDetails(mobileNumber);
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(customerDto);
	}
	

	@Operation(
            summary = "Update Account Details REST API",
            description = "REST API to update Customer &  Account details based on a account number"
    )
	 @ApiResponses({
         @ApiResponse(
                 responseCode = "200",
                 description = "HTTP Status OK"
         ),
         @ApiResponse(
                 responseCode = "417",
                 description = "Expectation Failed"
         ),
         @ApiResponse(
                 responseCode = "500",
                 description = "HTTP Status Internal Server Error",
                 content = @Content(
                         schema = @Schema(implementation = ErrorResponseDto.class)
                 )
         )
 }
 )
	@PutMapping("/update")
	//They can change any of the data except account number
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        Boolean isUpdated=iAccountsService.updateAccount(customerDto);
        
        if(isUpdated) {
        	return ResponseEntity
        			.status(HttpStatus.OK)
        			.body(new ResponseDto(AccountsConstants.MESSAGE_200,AccountsConstants.MESSAGE_200));
        }else {
        	 return ResponseEntity
                     .status(HttpStatus.EXPECTATION_FAILED)
                     .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
        
    }
	@Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer &  Account details based on a mobile number"
    )
	@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "417",
                description = "Expectation Failed"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
}
)
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseDto> deleteAcountDetails(@RequestParam @Pattern(regexp="(^$|[0-9]{10})",message="Mobile number must be 10 digits") String mobileNumber){
		 Boolean isDeleted=iAccountsService.deleteAcountDetails(mobileNumber);
		 
		 if(isDeleted) {
	        	return ResponseEntity
	        			.status(HttpStatus.OK)
	        			.body(new ResponseDto(AccountsConstants.STATUS_200,AccountsConstants.MESSAGE_200));
	        }else {
	        	 return ResponseEntity
	                     .status(HttpStatus.EXPECTATION_FAILED)
	                     .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
	        }
	}
	
	@Operation(
            summary = "Get Build Information",
            description = "Get build information that is deployed into accounts microservice"
    )
	@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
}
)
	
	@GetMapping("/build-info")
	public ResponseEntity<String> getBuildInfo(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(buildVersion);	}
	
	
	@Operation(
            summary = "Get Java version",
            description = "Get java version that is installed into accounts microservice"
    )
	@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
}
)
	@GetMapping("/java-version")
	public ResponseEntity<String> getJavaVersion(){
		return ResponseEntity
				.status(HttpStatus.OK)
				.body(environment.getProperty("JAVA_HOME"));	}
	

}
