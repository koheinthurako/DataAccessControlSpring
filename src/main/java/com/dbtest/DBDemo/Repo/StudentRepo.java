package com.dbtest.DBDemo.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.dbtest.DBDemo.DTO.StdAvg;
import com.dbtest.DBDemo.entities.Student;

@Repository
//	@Repository နဲ့ အောက်က @EnableJpaRepositories လို့ရေးလိုက်တာက Dependency Injection ရချင်လို့ဖြစ်တယ်
//	Dependency Injection ရမှသာ customize method တွေကိုရေးပြီး အလုပ်လုပ်ခိုင်းနိုင်တယ်
//	@Repository annotation ကတော့ spring နဲ့ DB ကြားမှာ object တစ်ခုဆောက်ပေးပြီး ချိတ်ဆက်အလုပ်လုပ်ပေးနိုင်ဖို့ဖြစ်တယ်
@EnableJpaRepositories
//	@EnableJpaRepositories ကတော့ JPA Library ထဲမှာမပါတဲ့ customize method တွေရေးနိုင်ဖို့ထည့်ပေးရတာမျိုးဖြစ်တယ်
public interface StudentRepo extends JpaRepository<Student, Integer> {
	
//	JpaRepository<database table name, table primary key ရဲ့ data type> => Spring နဲ့ DB ကို ချိတ်ပေးတဲ့ HIBERNATE
//	HiBernate is an implementation of JPA that provides a high-level API for interacting with databases
//	JpaRepository က Spring framework ကို HiBernate နဲ့လှမ်းချိတ်ပေးပြီး SQL တွေကို သူ့ထဲမှာပဲ run ပေးနိုင်တယ်
//	အဲ့တော့ Student table တစ်ခုကိုဆောက်ပေးနိုင်ဖို့ jpaRepository ကို extends လုပ်လိုက်တာနဲ့ <> ထဲမှာ ထည့်လိုက်တဲ့ class ကို
//	HiBernate မှတစ်ဆင့် အလုပ်လုပ်ပြီး database ထဲမှာ table auto ဆောက်ပေးသွားတယ် အပေါ်က Annotation တွေမထည့်ရင်တောင် table ဆောက်ပေးနိုင်တယ်
	
	
//	Customize method working together with repository HIBERNATE 
	public Student findByName(String name);
	
	@Query(value = "select * from student where english>? and myanmar>? and mathematics>?;", nativeQuery = true)
	public List<Student> getDistinction(int mark, int mark1, int mark2);
	
//	Wrapper Integer class ကို သုံးပြီး အောက်ကလို ရေးရင် query အဆုံးမှာ ";" semicolon ပါလို့မရတာ အထူးသတိထားပါ
//	ပါသွားရင် internal server error တက်မယ်
	@Query(value = "select * from student where english>?1 and myanmar>?1 and mathematics>?1", nativeQuery = true)
	public List<Student> getPass(Integer mark);
	
//	DB ကနေ အောက်ကလို calculation တစ်ခုခုလုပ်လိုက်လို့ ဟိုဘက်က return ပြန်လာမယ့် type က ရောနေမယ်/မရှိဘူးဆိုရင် interface တစ်ခုထုတ်ပြီးလုပ်နိုင်တယ်
//	အောက်က calculation လုပ်ပြီးရလာတဲ့ name နဲ့ double data types တွေကို ဆောက်လိုက်တဲ့ interface ဘက်မှာ လက်ခံဖို့လိုတယ်
//	StdAvg.java ထဲမှာသွားကြည့် အဲ့လိုလက်ခံအောင် နာမည်တွေရဲ့ ရှေ့မှာ get ခံပြီးတော့ အောက်မှာရေးထားတဲ့နာမည်အတိုင်း လိုက်ရေးပေးမှသာ အမှန်အလုပ်လုပ်နိုင်မယ်
//	ဥပမာ - getName(), getAverage() ဆိုတဲ့ interface ထဲက abstract method ပုံစံမျိုးတွေရေးပေးမှသာ hiBernate က အလုပ်လုပ်ပေးနိုင်တယ်
	@Query(value = "select name, (english + myanmar + mathematics)/3 as Average from student where name='mgmg';", nativeQuery = true)
	public StdAvg getAvg();
	
}
