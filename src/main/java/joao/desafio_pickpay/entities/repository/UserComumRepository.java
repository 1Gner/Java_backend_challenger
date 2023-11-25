package joao.desafio_pickpay.entities.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import joao.desafio_pickpay.entities.User;

@Repository
public interface UserComumRepository extends JpaRepository<User, Long> {
}
