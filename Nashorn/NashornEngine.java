package com.rzt;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class NashornEngine {

	static NashornEngine instance;
	ScriptEngineManager engineManager;
	ScriptEngine engine;

	private NashornEngine()
	{
		this.engineManager = new ScriptEngineManager();
		this.engine = engineManager.getEngineByName("nashorn");
	}

	public static NashornEngine getInstance()
	{
		if( instance == null )
			instance = new NashornEngine();
		return instance;
	}

	public Object runScript( String script ) throws ScriptException
	{
		if( script == null )
			return null;
		return this.engine.eval(script);
	}

	public Object runScriptFile( String scriptPath ) throws ScriptException
	{
		if( scriptPath == null )
			return null;
		return this.engine.eval("load(\"" + scriptPath + "\");");

	}
}
