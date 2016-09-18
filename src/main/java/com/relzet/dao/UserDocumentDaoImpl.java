package com.relzet.dao;

import com.relzet.model.UserDocument;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.*;

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
			if (ud.getType().equals("folder")) {
				int files = 0;
				double size = 0d;
				for(UserDocument doc : findDocsInFolder(userId, ud.getId())){
					files++;
					size+=doc.getContent().length;
				}
				ud.setInfo(files+" files<br>"+ ((int) (size / 1000))+" Kb");
				result.add(ud);
			}
		}

		return result;
	}

	@Override
	public List<UserDocument> findDocsInFolder(int userId, int docId) {
		List<UserDocument> result = new ArrayList<UserDocument>();

		for(UserDocument ud: findAllInFolder(userId, docId)) {
			if (!ud.getType().equals("folder")) {
				ud.setInfo(ud.getContent().length/1000+" Kb");
				result.add(ud);
			}
		}

		return result;
	}

	@Override
	public List<UserDocument> searchFoldersInFolder(int userId, int docId, String target) {
		List<UserDocument> result = new ArrayList<>();

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
				case "documents": formats.addAll(new ArrayList<>(Arrays.asList("text", "plain", "pdf", "officedocument", "msword"))); break;
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

	@Override
	public boolean checkFolderNameUnique(int userId, int docId, String folderName) {
		for (UserDocument ud : findFoldersInFolder(userId, docId)) {
			if (ud.getName().equals(folderName)) return true;
		}
		return false;
	}


	public List<UserDocument> getSortedDocs(int userId) {
		List<UserDocument> result = findAllByUserId(userId);

		Collections.sort(result, new Comparator<UserDocument>() {
			@Override
			public int compare(UserDocument o1, UserDocument o2) {
				return o2.getContent().length - o1.getContent().length;
			}
		});
		return result;
	}


}
