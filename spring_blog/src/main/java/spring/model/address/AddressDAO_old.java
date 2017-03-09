package spring.model.address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.stereotype.Repository;

import spring.utility.blog.DBClose;
import spring.utility.blog.DBOpen;

@Repository
public class AddressDAO_old {
	public boolean d(int no){
		boolean f=false;
		Connection c=DBOpen.open();
		PreparedStatement p=null;
		StringBuffer sql= new StringBuffer();
		sql.append(" delete from address ");
		sql.append(" where no = ? ");
		try {
			p=c.prepareStatement(sql.toString());
		p.setInt(1, no);
		int cnt=p.executeUpdate();
		if(cnt>0){
			f=true;
		}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBClose.close(c, p);
		}
		return f;
	}
	public boolean u(AddressDTO dto){
		boolean f = false;
		Connection c=DBOpen.open();
		PreparedStatement p=null;
		StringBuffer sql= new StringBuffer();
		sql.append(" update address set phone=?,address2=?,address1=?,zipcode=? ");
		sql.append(" where no =?  ");
		try {
			p=c.prepareStatement(sql.toString());
			p.setString(1,dto.getPhone());
			p.setString(2,dto.getAddress2());
			p.setString(3,dto.getAddress1());
			p.setString(4,dto.getZipcode());
			p.setInt(5,dto.getNo());
			int cnt=p.executeUpdate();
			if(cnt>0){
				f=true;
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			DBClose.close(c, p);
		}
		
		return f;
	}
	
	
	
	
	public boolean c(AddressDTO dto){
		boolean f=false;
	    Connection c=DBOpen.open();
	    PreparedStatement p=null;
	    StringBuffer sql=new StringBuffer();
	    sql.append(" insert into address(no,name,phone,address1,address2,zipcode,wdate) ");
	    sql.append(" values ((select nvl(max(no),0)+1 from address),?,?,?,?,?,sysdate) ");
	    
	    try {
			p=c.prepareStatement(sql.toString());
			p.setString(1,dto.getName());
			p.setString(2,dto.getPhone());
			p.setString(3,dto.getAddress1());
			p.setString(4,dto.getAddress2());
			p.setString(5,dto.getZipcode());
			int cnt=p.executeUpdate();
			if(cnt>0){
				f=true;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			DBClose.close(c, p);
		}
		return f;
	}
	public AddressDTO r(int no){
		AddressDTO dto=new AddressDTO();
		Connection c= DBOpen.open();
		PreparedStatement p=null;
		ResultSet r=null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from address ");
		sql.append(" where no=? ");
		try {
			p=c.prepareStatement(sql.toString());
			p.setInt(1,no);
			r=p.executeQuery();
			if(r.next()){
				dto.setName(r.getString("name"));
				dto.setPhone(r.getString("phone"));
				dto.setAddress2(r.getString("address2"));
				dto.setAddress1(r.getString("address1"));
				dto.setZipcode(r.getString("zipcode"));
				dto.setWdate(r.getString("wdate"));
				dto.setNo(r.getInt("no"));
				
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			DBClose.close(c, p, r);
		}
		
		
		
		return dto;
	}
	public int total(String col, String word){
		int total=0;
		Connection c= DBOpen.open();
		PreparedStatement ps=null;
		ResultSet rs=null;
		StringBuffer sql =new StringBuffer();
		sql.append(" select count(*)  from address ");
		if(word.trim().length()>0){
		sql.append(" where "+col+" like '%'||?||'%' ");
		}
		try {
			ps=c.prepareStatement(sql.toString());
			if(word.trim().length()>0){
				ps.setString(1,word);
			}
			rs=ps.executeQuery();
			if(rs.next()){
				total=rs.getInt(1);
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}finally {
			DBClose.close(c, ps, rs);
		}
		
		return total;
	}
	
	
public List<AddressDTO> l(Map map){
	List<AddressDTO> l= new ArrayList<AddressDTO>();
	Connection c=DBOpen.open();
    PreparedStatement ps=null;
    ResultSet r=null;
    int ano=(Integer) map.get("ano");
    int bno=(Integer) map.get("bno");
    String col = (String) map.get("col");
    String word = (String) map.get("word");
    StringBuffer sql=new StringBuffer();
    sql.append(" select no,name,phone,to_char(wdate,'yyyy-mm-dd') wdate,r from( ");
    sql.append(" select no,name,phone,wdate,rownum r from( ");
    sql.append(" select no,name,phone,wdate from address  ");
    if(word.trim().length()>0){
    	sql.append(" where "+col+" like '%'||?||'%' ");	
    }
    
    sql.append(" order by no desc  ");
    sql.append(" ))where r>=? and r<=? ");
    
	try {
		int i=1;
		ps=c.prepareStatement(sql.toString());
		if(word.trim().length()>0){
		ps.setString(i++,word);
		}
		ps.setInt(i++,ano );
		ps.setInt(i++,bno );
		r=ps.executeQuery();
		while(r.next()){
			AddressDTO dto=new AddressDTO();
			dto.setNo(r.getInt("no"));
			dto.setName(r.getString("name"));
			dto.setPhone(r.getString("phone"));
			dto.setWdate(r.getString("wdate"));
		l.add(dto);
		}
		
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}finally {
		DBClose.close(c, ps, r);
	}
	
	return l;
	
}
}
