package org.treez.javafxd3.d3.wrapper;

import org.treez.javafxd3.d3.core.Selection;

import javafx.geometry.BoundingBox;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;

/**
 * 
 *
 */
public class Element extends Node {

	//#region CONSTRUCTORS

	/**
	 * Constructor
	 * 
	 * @param webEngine
	 * @param wrappedJsObject
	 */
	public Element(WebEngine webEngine, JSObject wrappedJsObject) {
		super(webEngine, wrappedJsObject);
	}

	//#end region

	//#region METHODS

	public Node cloneNode(boolean b) {
		throw new IllegalStateException("not yet implemented");
	}

	public String getTagName() {
		String result = getMemberForString("tagName");
		return result;
	}

	public Node getParentNode() {
		String command = "d3.select(this).parentNode";
		JSObject result = evalForJsObject(command);
		return new Node(webEngine, result);
	}

	public int getChildCount() {

		try {
			String countCommand = "this.children.length";
			int result = evalForInteger(countCommand);
			return result;
		} catch (JSException exception) {
			String countCommand = "this.childNodes.length";
			int result = evalForInteger(countCommand);
			return result;
		}
	}

	public String getTextContent() {
		String result = getMemberForString("textContent");
		if (result.equals("undefined")) {
			result = getMemberForString("text");
		}
		return result;
	}

	public void setInnerHtml(String html) {
		String command = "this.innerHtml=\"" + html + "\";";
		eval(command);
	}

	public String getAttribute(String attr) {
		String command = "this.getAttribute('" + attr + "')";
		String result = evalForString(command);
		return result;
	}

	public Object getProperty(String property) {
		String command = "this." + property;
		return eval(command);
	}

	public void setPropertyBoolean(String dataProperty, boolean bool) {
		String command = "this." + dataProperty + "= " + bool + ";";
		eval(command);
	}

	public Integer getPropertyInt(String dataProperty) {
		String command = "this." + dataProperty;
		Integer result = evalForInteger(command);
		return result;
	}

	public void setPropertyInt(String dataProperty, int value) {
		String command = "this." + dataProperty + "= " + value + ";";
		eval(command);
	}

	public String getPropertyString(String dataProperty) {
		String command = "this." + dataProperty;
		String result = evalForString(command);
		return result;
	}

	public Element getChild(int index) {
		String command = "this.childNodes[" + index + "];";
		JSObject child = evalForJsObject(command);
		return new Element(webEngine, child);
	}

	public String getChildNodes() {
		String command = "this.childNodes";
		String result = evalForString(command);
		return result;
	}

	public Element[] getElementsByTagName(String string) {
		throw new IllegalStateException("not yet implemented");
	}

	public void add(D3NodeFactory nodeFactory) {
		nodeFactory.createInParentSelection(select());
	}

	public void remove(D3NodeFactory nodeFactory) {
		Selection elementSelection = select();
		nodeFactory.remove(elementSelection);
	}

	public Selection select() {
		JSObject d3Obj = this.getD3();
		JSObject selectionObj = (JSObject) d3Obj.call("select", getJsObject());
		Selection elementSelection = new Selection(webEngine, selectionObj);
		return elementSelection;
	}

	public BoundingBox getBBox() {
		String command = "this.getBBox();";
		JSObject bBox = evalForJsObject(command);

		Double x = Double.parseDouble("" + bBox.eval("this.x"));
		Double y = Double.parseDouble("" + bBox.eval("this.y"));
		Double width = Double.parseDouble("" + bBox.eval("this.width"));
		Double height = Double.parseDouble("" + bBox.eval("this.height"));
		BoundingBox boundinbBox = new BoundingBox(x, y, width, height);
		return boundinbBox;
	}

	public Element getParentElement() {
		String command = "this.parentNode";
		JSObject result = evalForJsObject(command);
		return new Element(webEngine, result);
	}

	public String getStyle(String identifier) {
		String command = "this.style." + identifier;
		String result = evalForString(command);
		return result;
	}

	//#end region

}