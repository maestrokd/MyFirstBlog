package blog.model.repository;

import blog.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Integer> {

    // findBy[real field name]!!!
    Role findRoleByName(String name);

    Role findRoleById(int id);
}



