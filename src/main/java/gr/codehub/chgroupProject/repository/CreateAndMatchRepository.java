package gr.codehub.chgroupProject.repository;

import gr.codehub.chgroupProject.model.CreateAndMatch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreateAndMatchRepository extends JpaRepository<CreateAndMatch, Integer> {

}
