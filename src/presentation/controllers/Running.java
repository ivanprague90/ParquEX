package presentation.controllers;

import net.sf.clipsrules.jni.Environment;

public class Running extends Thread{
	public void run (Environment clips) {
		clips.run();
		System.out.println("HEILA");
	}
}
