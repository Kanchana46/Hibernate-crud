package com.code.HibernateCRUD;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;



public class App 
{
    public static void main( String[] args )
    {
    	Configuration con = new Configuration().configure().addAnnotatedClass(UserDetails.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
        SessionFactory sessionFactory = con.buildSessionFactory(serviceRegistry);       
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        
       /* for( int i=0; i<=10;i++) {    // Save user
        	UserDetails ud = new UserDetails();
        	ud.setName("user "+i);
        	session.save(ud);
        }
        */
        UserDetails user = session.get(UserDetails.class, 7);  // Get user
        System.out.println(user);
        
        //session.delete(session.get(UserDetails.class, 7)); // Delete user
        
        UserDetails userdetail = session.get(UserDetails.class, 4);  // Update user
        userdetail.setName("Updated user 4");
        session.update(userdetail);
        
        //HQL
        Query query = session.createQuery("from UserDetails");
        List users = query.list();
        
        session.getTransaction().commit();
        session.close();
        
        System.out.println(users);
    }
}
