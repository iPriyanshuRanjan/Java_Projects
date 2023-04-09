package com;

import org.logicalcobwebs.proxool.admin.Admin;

public interface AdminDao {
	public boolean validateAdmin(Admin admin);
	public Integer addAdmin(Admin admin);
	public void updateAdminPassword(String email, String password);
	public void deleteAdmin(Integer adminId);
	public Admin searchAdminByEmail(String email);
}
