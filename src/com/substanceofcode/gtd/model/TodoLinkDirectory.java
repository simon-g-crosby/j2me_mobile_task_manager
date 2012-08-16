package com.substanceofcode.gtd.model;

import java.util.Hashtable;

public class TodoLinkDirectory {
	
	private Hashtable items = new Hashtable(); 
	// private 
	
	public void addTodoItem(long todoItemId, TodoItem todoItem)
	{
		items.put(new Long(todoItemId), todoItem);
	}
	
	public get
}
