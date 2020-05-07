package com.allan.github;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.kohsuke.github.GHUser;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.PagedIterable;

public class GitHubTest {
	@Test
	public void test() throws IOException {
		GitHub github = GitHub.connect();
		GHUser user = github.getUser("brad");
		PagedIterable<GHUser> list = github.searchUsers().q("brad").list();
		System.out.println(user);
		System.out.println(list.toList().size());
		list.forEach(System.out::println);
	}
}
