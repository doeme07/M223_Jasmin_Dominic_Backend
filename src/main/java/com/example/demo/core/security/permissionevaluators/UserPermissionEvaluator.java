package com.example.demo.core.security.permissionevaluators;

import com.example.demo.domain.mylistentry.MyListEntry;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserPermissionEvaluator {

  public UserPermissionEvaluator() {
  }

  public boolean isUserAboveAge(User principal, int age) {
    return true;
  }

  public boolean isOwnPost(MyListEntry targettedMyListEntry) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetailsImpl currentUser = (UserDetailsImpl) authentication.getPrincipal();
    User user = currentUser.user();
    System.out.println(user.getId());
    System.out.println(targettedMyListEntry.getUser().getId());

    return user.getId().toString().equals(targettedMyListEntry.getUser().getId().toString());
  }
}
