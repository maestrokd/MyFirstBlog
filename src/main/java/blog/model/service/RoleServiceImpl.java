package blog.model.service;

import blog.model.entity.Role;
import blog.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl {

    // Fields
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    // Setters
    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    // Methods
    public List<Role> findAll() {
        List<Role> roleList = roleRepository.findAll();
//        System.out.println("RoleService.findAll: " + roleList);
        return roleList;
    }

}
