package soulland.project.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import soulland.project.entity.Role;
import soulland.project.entity.RoleName;
import soulland.project.entity.User;


@Repository
public interface RoleRepository  extends JpaRepository<Role,Long>{
	Optional<Role> findByName(RoleName name);
	List<User> findUsersByName(RoleName name);
}
