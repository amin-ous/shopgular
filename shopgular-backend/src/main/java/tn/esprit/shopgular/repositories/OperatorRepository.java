package tn.esprit.shopgular.repositories;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;
import tn.esprit.shopgular.entities.*;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Long> {}
