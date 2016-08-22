package com.relzet.dao;

import com.relzet.model.UserDocument;

import java.util.List;

public interface UserDocumentDao {

	List<UserDocument> findAll();
	
	UserDocument findById(int id);
	
	void save(UserDocument document);
	
	List<UserDocument> findAllByUserId(int userId);
	
	void deleteById(int id);

	List<UserDocument> findAllInFolder(int userId, int docId);

	UserDocument findRootByUserId(int userId);
}
