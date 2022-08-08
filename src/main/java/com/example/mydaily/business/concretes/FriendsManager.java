package com.example.mydaily.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mydaily.business.abstracts.FriendsService;
import com.example.mydaily.dataaccess.FriendsDao;
import com.example.mydaily.dataaccess.UserDao;
import com.example.mydaily.entities.Friends;
import com.example.mydaily.entities.User;

@Service
public class FriendsManager implements FriendsService {

	private final FriendsDao friendsDao;
	private final UserDao userDao;
	
	public FriendsManager(FriendsDao friendsDao,UserDao userDao) {
		this.friendsDao=friendsDao;
		this.userDao=userDao;
		
	}
	
	
	@Override
	public List<Friends> getAllFriends(Long userid) {
		List <Friends> list;
		
		
		list = friendsDao.findByUserId(userid);
	
		return list;
		
	}

	@Override
	public Friends AddFriedns(Long userid , Long friendsid) {
		Optional<User> user = userDao.findById(userid);
		Optional<Friends> friends = friendsDao.findById(friendsid);
		
		if(user.isPresent() && friends.isPresent()) {
			User findedUser = user.get();
			List<Friends> findedFriends = (List<Friends>) friends.get();
			
			findedUser.setFriends((List<Friends>) findedFriends);
		}
		
		return null;
		
	}

	@Override
	public void deleteFriend(Long friendsid) {
		friendsDao.deleteById(friendsid);
		
	}

	@Override
	public Friends getOneFriendsById(Long friendsid) {
		
		return friendsDao.findById(friendsid).get();
	}

	@Override
	public Friends getOneFriendsByName(String friendsName) {
		
		return friendsDao.findByFriendsName(friendsName);
	}

}
