package com.allan.jgit;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.eclipse.jgit.errors.UnsupportedCredentialItem;
import org.eclipse.jgit.transport.CredentialItem;
import org.eclipse.jgit.transport.URIish;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.jupiter.api.Test;

public class JgitTest {
	@Test
	public void test() throws IOException, InvalidRemoteException, TransportException, GitAPIException {
		String url = "https://github.com/allan13cakes/chocolate.git";
		String branch = "master";
		File tempDir = new File("/Users/qiuguozhong/git_repository_test/myrepo_3");
		String userName = "jlyq2016@163.com";
		String password = "qgz6641646";

		CloneCommand clone = Git.cloneRepository();
		MyProgressMonitor monitor = new MyProgressMonitor();
		clone.setURI(url);
		clone.setBranch(branch);
		clone.setDirectory(tempDir);
		clone.setProgressMonitor(monitor);

		UsernamePasswordCredentialsProvider usernamePasswordCredentialsProvider = new UsernamePasswordCredentialsProvider(
				userName, password) {

			@Override
			public boolean isInteractive() {
				return false;
			}

			@Override
			public boolean supports(CredentialItem... items) {
				for (CredentialItem item : items) {
					if (item instanceof CredentialItem.InformationalMessage) {
						continue;
					}
					if (item instanceof CredentialItem.YesNoType) {
						continue;
					}
					return false;
				}
				return true;
			}

			@Override
			public boolean get(URIish uri, CredentialItem... items) throws UnsupportedCredentialItem {
				for (CredentialItem item : items) {
					if (item instanceof CredentialItem.InformationalMessage) {
						continue;
					}
					if (item instanceof CredentialItem.YesNoType) {
						((CredentialItem.YesNoType) item).setValue(true);
						continue;
					}
					return false;
				}
				return true;
			}

		};
		clone.setCredentialsProvider(usernamePasswordCredentialsProvider);
		Git git = clone.call();
	}
}
