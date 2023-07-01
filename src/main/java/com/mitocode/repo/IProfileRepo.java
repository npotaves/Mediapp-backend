package com.mitocode.repo;

import com.mitocode.model.Menu;
import com.mitocode.model.Role;
import com.mitocode.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProfileRepo extends IGenericRepo<Role, Integer> {

    @Query(value = """
            select r.* from role r
            inner join user_role ur on ur.id_role = r.id_role
            inner join user_data u on u.id_user = ur.id_user
            where u.username = :username
            """, nativeQuery = true)
    List<Role> getRoleByUsername(@Param("username") String username);

}
