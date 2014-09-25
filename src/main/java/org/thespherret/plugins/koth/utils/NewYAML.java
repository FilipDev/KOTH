package org.thespherret.plugins.koth.utils;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class NewYAML {
	private YamlConfiguration yamlFile;
	private File file;

	public NewYAML(File f)
	{
		this.file = f;
	}

	public YamlConfiguration newYaml()
	{
		if (!file.exists())
		{
			try{
				boolean worked1 = file.getParentFile().mkdirs();
				boolean worked2 = file.createNewFile();
				if (worked1 || worked2) throw new IOException("Could not create the new file.");
			}catch (IOException e){
				e.printStackTrace();
				return null;
			}
		}
		this.yamlFile = YamlConfiguration.loadConfiguration(file);
		return this.yamlFile;
	}

	public YamlConfiguration newYaml1()
	{
		this.yamlFile = YamlConfiguration.loadConfiguration(file);
		return this.yamlFile;
	}

	public File getFile()
	{
		return this.file;
	}
	public boolean exists(ConfigurationSection cs)
	{
		if (cs.isConfigurationSection(cs.getCurrentPath())){
			return true;
		}else{
			return false;
		}
	}

}
