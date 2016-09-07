package com.relzet.service;

import com.relzet.model.UserDocument;

import java.util.List;

public interface UserDocumentService {

	UserDocument findById(int id);

	List<UserDocument> findAll();
	
	List<UserDocument> findAllByUserId(int id);
	
	void saveDocument(UserDocument document);
	
	void deleteById(int id);

	List<UserDocument> findAllInFolder(int userId, int docId);

	UserDocument findRootByUserId(int userId);

	List<UserDocument> findFoldersInFolder(int userId, int docId);

	List<UserDocument> findDocsInFolder(int userId, int docId);

	List<UserDocument> searchFoldersInFolder(int userId, int docId, String target);

	List<UserDocument> searchDocsInFolder(int userId, int docId, String target);


	List<UserDocument> filterDocsInFolder(int userId, int docId, String[] filters);

}
