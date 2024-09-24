package com.demo.serviceClass;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.demo.DataTransferObjects.studentDTO;


public class userDetailsServiceImpl implements UserDetailsService {
	
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private List<studentDTO> query;
	

	@Override
	//Here the argument 'String username' is collected from the 'username' field from login page.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		String sql="select * from student where email=?";
//		studentDTO studentDTOObj = jdbcTemplate.queryForObject
//				(sql, new BeanPropertyRowMapper<>(studentDTO.class),username);
		
		// the 'queryForObject' expects atleast one record to be returned.
		//Due to this problem, if we supply the non-existed user from the login page
		//the control does not flows through the below null check
		
		
		
		
		
//		if(studentDTOObj==null)
//		{
//			throw new RuntimeException("User does not exists!!!");
//			
//		}
		
		
		// to resolve this issue, we are using the below 'jdbcTemplate.query()'
		
			List<studentDTO>studentDTOList = jdbcTemplate.
					query(sql, new BeanPropertyRowMapper<>(studentDTO.class),username);
			
			
			if(studentDTOList.size()==0)
			{
				throw new RuntimeException("user does not exists!!");
			}
		
		
		UserDetails userDetails = User.withUsername(studentDTOList.get(0).getStudentname())
		.password(studentDTOList.get(0).getPassword())
		.roles(studentDTOList.get(0).getRoles())
		.build();
	
		return userDetails;
	}

}
