package org.barmaley.vkr.service;

import org.apache.log4j.Logger;
import org.barmaley.vkr.domain.StudentCopy;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by SUN_SUN on 06.05.2017.
 */
@Service("studentCopyService")
@Transactional
public class StudentCopyService {
    protected static Logger logger = Logger.getLogger("service");

    @Resource(name="sessionFactory")
    private SessionFactory sessionFactory;

    public void add(StudentCopy studentCopy) {
        logger.debug("Editing existing studentCopy");

        // Retrieve session from Hibernate
        // как всегда получаем сессию
        Session session = sessionFactory.getCurrentSession();

        // Retrieve existing person via id
        // получаем существующую персону по id
        StudentCopy existingStudentCopy = (StudentCopy) session.get(StudentCopy.class, studentCopy.getUsername());

        // Assign updated values to this person
        // обновляем значения
        // Save updates
        // сохраняем изменения
        session.save(existingStudentCopy);
    }
}
