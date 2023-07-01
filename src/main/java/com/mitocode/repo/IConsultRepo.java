package com.mitocode.repo;

import com.mitocode.dto.ConsultProcDTO;
import com.mitocode.dto.IConsultProcDTO;
import com.mitocode.model.Consult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IConsultRepo extends IGenericRepo<Consult, Integer> {

    //FRT: jaime
    //BD: jaime toLowerCase
    //SELECT * FROM Consult c INNER JOIN Patient p ON c.id_patient = p.id_patient WHERE p.dni = ?
    @Query("FROM Consult c WHERE c.patient.dni = :dni OR LOWER(c.patient.firstName) LIKE %:fullname% OR LOWER(c.patient.lastName) LIKE %:fullname%") //JPQL
    List<Consult> search(@Param("dni") String dni, @Param("fullname") String fullname);

    // >=      |  <
    //06-04-23 | 29-04-23 | 30
    @Query("FROM Consult c WHERE c.consultDate BETWEEN :date1 AND :date2")
    List<Consult> searchByDates(@Param("date1") LocalDateTime date1, @Param("date2") LocalDateTime date2);

    @Query(value = "select * from fn_list()", nativeQuery = true)
    List<Object[]> callProcedureOrFunctionNative();

    @Query(value = "select * from fn_list()", nativeQuery = true)
    List<IConsultProcDTO> callProcedureOrFunctionProjection();

    @Procedure(procedureName = "fn_list")
    List<IConsultProcDTO> callProcedure();
    /*
    [5,	"01/04/2023"]
    [1,	"22/04/2023"]
    [3,	"29/04/2023"]
     */
}
