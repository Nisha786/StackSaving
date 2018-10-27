package com.ri;
import java.util.*;
import javax.inject.Provider;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.EntityType;
import com.ri.util.JpaUtil;
import com.ri.entity.*;
public class CriteriaQuery {
public static void main(String[] args) {
	
EntityManager em=null;

try{
	 em=JpaUtil.getEntityManagerFactory().createEntityManager();
	
	 em.getTransaction().begin();
		

	Department dep =new Department();
	dep.setDeptId(1011);
	dep.setDeptName("Computer Science");
	
	
	Employee emp = new Employee();
	emp.setEmpId(101);
	emp.setEmpName("Computer Science");
	emp.setEmpAdd("Indore");
	
	Employee emp1 = new Employee();
	emp1.setEmpId(102);
	emp1.setEmpName("Ashish");
	emp1.setEmpAdd("satna");
	
	Employee emp2 = new Employee();
	emp2.setEmpId(103);
	emp2.setEmpName("rishi");
	emp2.setEmpAdd("satna");
	
	Set set = new HashSet();
	set.add(emp);
	set.add(emp1);
	set.add(emp2);

	dep.setEmployees(set);
	
	em.persist(dep);

	/////"**** Criteria Query *****/////

	/*
	CriteriaBuilder builder=em.getCriteriaBuilder();	 
	CriteriaQuery <Employee> query=builder.createQuery(Employee.class);
	Root <Employee> root=query.from(Employee.class);
	query.select(root).distinct(true);
	query.where(builder.equal(root.get("empName"), "kapil")).orderBy(builder.asc(root.get("empName")));
		
	TypedQuery q=em.createQuery(query);
	
	List<Employee>emplist=q.getResultList();
	for(Employee su:emplist) {
		
		System.out.println(su.getEmpId());
		System.out.println(su.getEmpName());
	}	
			
	/*
	
	
	 ////////////// <!=== Between ==>///////////////
	  
	/*  
	CriteriaBuilder builder1 = em.getCriteriaBuilder();
	CriteriaQuery<Employee> criteriaquery1=builder1.createQuery(Employee.class);
	Root<Employee>root1=criteriaquery1.from(Employee.class);
	criteriaquery1.select(root1);
	criteriaquery1.where(builder.between(root.get ("empId"), 101,102)).orderBy(builder.desc(root.get("empName")));
	TypedQuery q1 =em.createQuery(criteriaquery1);
	
	List <Employee> deplist =q1.getResultList();
	
	for(Employee su1:deplist) {
 		System.out.println(su1.getEmpName());
		
	}*/
	
		
	////<!=====LIKE  ========= >
	
	/*CriteriaBuilder builder2 =em.getCriteriaBuilder();
	javax.persistence.criteria.CriteriaQuery<Employee> criteriaquery2 =builder2.createQuery(Employee.class);	
	Root <Employee>  root2=criteriaquery2.from(Employee.class );
	criteriaquery2.select(root2.get("empName"));
	criteriaquery2.where(builder2.like(root2.get("empName"),"Ashish"));
	criteriaquery2.distinct(true);
	TypedQuery<Employee> q2 =em.createQuery(criteriaquery2);
	
	List<Employee> emplist1=q2.getResultList();
	
	for(Employee hi:emplist1) {
		System.out.println(hi.getEmpName());
		System.out.println(hi.getEmpAdd());

	}*/
	
	
	/*CriteriaBuilder builder12 =em.getCriteriaBuilder();
	CriteriaQuery<Long> criteriaquery12 =builder12.createQuery(Long.class);	
	Root <Employee> root12=criteriaquery12.from(Employee.class );
	criteriaquery12.multiselect(builder12.count(root12));
	
	TypedQuery<Long> q12 =em.createQuery(criteriaquery12);
	
	long Count =q12.getSingleResult();
	System.out.println(Count);
	
	*/
	
	/////<!=======MultiSelect empId, empAdd..======>
	
	CriteriaBuilder builder3=em.getCriteriaBuilder();
	javax.persistence.criteria.CriteriaQuery<Object[]> criteriaquery3=builder3.createQuery(Object[].class);
	Root<Employee>root3 =criteriaquery3.from(em.getMetamodel().entity(Employee.class));
	criteriaquery3.multiselect(root3.get("empId"),root3.get("empAdd"));
	criteriaquery3.where(builder3.and(builder3.equal(root3.get("empName"),"Ashish"),builder3.equal(root3.get("empAdd"),"satna")));
	
	TypedQuery<Object[]>q3=em.createQuery(criteriaquery3);
	List<Object[]> emlist3=q3.getResultList();
	for(Object[] suuu:emlist3) {
		System.out.println("Name :" +suuu[0]);
		System.out.println("Address :"+suuu[1]);		
	
	}
	
	
	//<!-------Equal id=103;.....>/////
	
	/*CriteriaBuilder builder4=em.getCriteriaBuilder();
	CriteriaQuery<Department> criteriaquery4=builder4.createQuery(Department.class);
	Root<Department>root4 =criteriaquery4.from(Department.class);
	root4.join(,JoinType.INNER);
	criteriaquery4.select(root4).distinct(true);
	criteriaquery4.where(builder4.equal(root4.get("empId"),103));
	TypedQuery<Department>q4=em.createQuery(criteriaquery4);
	
	List<Department> emlist4=q4.getResultList();
	
	for(Department ju:emlist4) {
		System.out.println(ju.getDeptName());
	}
*/


/////////    <<<<< Join Criteria Query using Root>>  ///// ///////
	
	/*CriteriaBuilder builder5 =em.getCriteriaBuilder();
	CriteriaQuery<Tuple> criteriaquery5 =builder5.createTupleQuery();
	Root<Employee> emproot =criteriaquery5.from(Employee.class);
	Root<Department> deproot =criteriaquery5.from(Department.class);
	criteriaquery5.multiselect(emproot,deproot).
	where(builder5.equal(emproot.get("empId"),deproot.get("deptId")));
	
	TypedQuery <Tuple> tq=em.createQuery(criteriaquery5);
	
	List<Tuple> objlist = tq.getResultList();
	
	for(Tuple oo:objlist) {
		
		Employee empemp=(Employee)oo.get(0);
		Department depdep=(Department)oo.get(1);
		System.out.println("Employee Name:"+empemp.getEmpName());
		System.out.println("Department Name:"+depdep.getDeptName());
		
	}*/

	
	/*
	CriteriaBuilder builder6 = em.getCriteriaBuilder();
	CriteriaQuery<Department> criteriaquery6 =builder6.createQuery(Department.class);
	Root<Employee> emproot =criteriaquery6.from(Employee.class);
	Root<Department> deproot=criteriaquery6.from(em.getMetamodel().entity(Department.class));
Join<Department, Employee> tasks = deproot.join("employees");
	
	criteriaquery6.select(deproot);
	criteriaquery6.where(builder6.equal(tasks.get("empName"),deproot.get("deptName"))).distinct(true);
	TypedQuery<Department> tq=em.createQuery(criteriaquery6);
	List<Department> list=tq.getResultList();
	for(Department jo:list) {
		System.out.println(jo.getDeptName());
		System.out.println(jo.getDeptId());
	}*/
	
	/*		
	 	Employee eeemp = (Employee)jo[0];	 
		Department deep =(Department)jo[1];
		System.out.println(eeemp.getEmpName());
		System.out.println(deep.getDeptName());
		
		*/
	
	// ...............Named Query Exmaple  .......////
	
	/*List<Department> list =em.createNamedQuery("Hi",Department.class).getResultList();
	for(Department dee : list) {
		System.out.println(dee.getDeptName());
	}*/
	
	//// .........USe Tuple To Fetch   & Condition (TWO equals )........////////
	
	/*CriteriaBuilder builder3=em.getCriteriaBuilder();
	CriteriaQuery<Tuple> criteriaquery3=builder3.createTupleQuery();
	Root<Employee>root3 =criteriaquery3.from(Employee.class);
	criteriaquery3.multiselect(root3.get("empId").alias("Id"),root3.get("empAdd").alias("Add"));
	criteriaquery3.where(builder3.and(builder3.equal(root3.get("empName"),"Ashish"),builder3.equal(root3.get("empAdd"),"satna")));

	TypedQuery<Tuple>q3=em.createQuery(criteriaquery3);	

	List<Tuple> emlist3=q3.getResultList();
	
	for(Tuple suuu:emlist3) {
	Integer Id=(Integer)suuu.get("Id");
	String Add=(String)suuu.get("Add");
	System.out.println("Add :"+ Add);
	System.out.println("Id: "+Id);
	
	}*/
	 
	/*JPAQuery query =new JPAQuery(em);
	QDepartment dept = QDepartment.dept;
	Department bob = queryFactory.selectFrom(dept)
	  .where(dept.deptName.equals("Ashish"))
	  .fetchOne();*/
	
	
	////........Criteria Delete Query.......///
	/*CriteriaBuilder builder10=em.getCriteriaBuilder();
	CriteriaDelete<Employee> criteriaquery10=builder10.createCriteriaDelete(Employee.class);
	Root root =criteriaquery10.from(Employee.class);
	criteriaquery10.where(builder10.equal(root.get("empId"), 103));
	TypedQuery q =(TypedQuery) em.createQuery(criteriaquery10);
	int rowcount=q.executeUpdate();
	System.out.println("Delet" + rowcount);
	*/
	
em.getTransaction().commit();
	
}catch(Exception e) {
	e.printStackTrace();
	if(em!=null) {
		System.out.println("Trasaction Rollback");
		em.getTransaction().rollback();
	}
}finally {
	if(em!=null) {
em.close();
}
}
JpaUtil.Shutdown();

}
}
