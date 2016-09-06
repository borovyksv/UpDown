package com.relzet.dao;

import com.relzet.model.UserDocument;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("userDocumentDao")
public class UserDocumentDaoImpl extends AbstractDao<Integer, UserDocument> implements UserDocumentDao{

	@SuppressWarnings("unchecked")
	public List<UserDocument> findAll() {
		Criteria crit = createEntityCriteria();
		return (List<UserDocument>)crit.list();
	}

	public void save(UserDocument document) {
		persist(document);
	}

	
	public UserDocument findById(int id) {
		return getByKey(id);
	}

	@SuppressWarnings("unchecked")
	public List<UserDocument> findAllByUserId(int userId){
		Criteria crit = createEntityCriteria();
		Criteria userCriteria = crit.createCriteria("user");
		userCriteria.add(Restrictions.eq("id", userId));
		return (List<UserDocument>)crit.list();
	}

	
	public void deleteById(int id) {
		UserDocument document =  getByKey(id);
		if (document.isFolder()) {
			for(UserDocument doc : findAllInFolder(document.getUser().getId(), id)){
				delete(doc);
			}
		}
		delete(document);
	}

	public List<UserDocument> findAllInFolder(int userId, int docId) {
		List<UserDocument> result = new ArrayList<UserDocument>();

		for(UserDocument ud: findAllByUserId(userId)) {
			if ((ud.getDescription()).equals(findById(docId).getDescription()+"."+ud.getName())) {
				result.add(ud);
			}
		}

		return result;
	}

	public UserDocument findRootByUserId(int userId) {
		for(UserDocument ud: findAllByUserId(userId)) {
			if (ud.getName().equals("ROOT")) return ud;

		}
		return null;
	}

	@Override
	public List<UserDocument> findFoldersInFolder(int userId, int docId) {
		List<UserDocument> result = new ArrayList<UserDocument>();

		for(UserDocument ud: findAllInFolder(userId, docId)) {
			if (ud.getType().equals("folder")) result.add(ud);
		}

		return result;
	}

	@Override
	public List<UserDocument> findDocsInFolder(int userId, int docId) {
		List<UserDocument> result = new ArrayList<UserDocument>();

		for(UserDocument ud: findAllInFolder(userId, docId)) {
			if (!ud.getType().equals("folder")) result.add(ud);
		}

		return result;
	}

	@Override
	public List<UserDocument> searchFoldersInFolder(int userId, int docId, String target) {
		List<UserDocument> result = new ArrayList<UserDocument>();

		for(UserDocument ud: findAllInFolder(userId, docId)) {
			if (ud.getType().equals("folder")&&ud.getName().contains(target)) result.add(ud);
		}

		return result;
	}

	@Override
	public List<UserDocument> searchDocsInFolder(int userId, int docId, String target) {
		List<UserDocument> result = new ArrayList<UserDocument>();

		for(UserDocument ud: findAllInFolder(userId, docId)) {
			if ((!ud.getType().equals("folder"))&&ud.getName().contains(target)) result.add(ud);
		}

		return result;
	}

	@Override
	public List<UserDocument> filterDocsInFolder(int userId, int docId, String[] filters) {
		List<UserDocument> result = new ArrayList<>();
		List<String> formats = new ArrayList<>();

		//here you can change filter formats
		for (String filter : filters) {
			switch (filter) {
				case "documents": formats.addAll(new ArrayList<>(Arrays.asList("text", "plain"))); break;
				case "pictures": formats.addAll(new ArrayList<>(Arrays.asList("image")));break;
				case "videos": formats.addAll(new ArrayList<>(Arrays.asList("video")));break;
				case "zip": formats.addAll(new ArrayList<>(Arrays.asList("zip")));break;
			}
		}


		for(UserDocument ud: findAllByUserId(userId)) {
			for (String format : formats)
			if (ud.getType().contains(format)) result.add(ud);
		}

		return result;


	}

}
