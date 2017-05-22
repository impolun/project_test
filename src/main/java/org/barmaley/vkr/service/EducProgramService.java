package org.barmaley.vkr.service;

import org.apache.log4j.Logger;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.barmaley.vkr.domain.EducProgram;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by gagar on 28.04.2017.
 */

@Service("educProgramService")
@Transactional
public class EducProgramService {

    protected static Logger logger = Logger.getLogger("service");

    @Resource(name = "sessionFactory")
    private SessionFactory sessionFactory;

    public List<EducProgram> getAll(String username){
        Session session = sessionFactory.getCurrentSession();
        logger.debug("Get users by institute");
        EducProgram educProgram = new EducProgram();
        SQLQuery query = session.createSQLQuery("SELECT * FROM EDUC_PROGRAM " +
                "WHERE ID IN(SELECT EDUC_PROGRAM_ID FROM STUDENT_EDUC_PROGRAMS WHERE STUDENT_ID='"+username+"')");
        query.addEntity(EducProgram.class);
        return query.list();
    }

    public EducProgram get(Integer id){
        Session session = sessionFactory.getCurrentSession();

        EducProgram educProgram = (EducProgram) session.get(EducProgram.class, id);

        return educProgram;
    }
}
