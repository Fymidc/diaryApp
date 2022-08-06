package com.example.mydaily.business.abstracts;

import java.util.List;


import com.example.mydaily.entities.Friends;


public interface FriendsService {
	List<Friends> getAllFriends(Long userid );
	Friends AddFriedns(Long userid,Long friendsid);
	void deleteFriend(Long friendsid);
	Friends getOneFriendsById(Long friendsid);
	Friends getOneFriendsByName(String friendsName);
}
