package com.mygdx.shadertest;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;

import java.awt.image.renderable.RenderContext;
import java.util.Random;

public class ShaderTest extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	private ShaderProgram shader; // Going to be applied to the sprite batch
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		ShaderProgram.pedantic = false;
//		shader = new ShaderProgram(Gdx.files.internal("shaders/red.vsh"),
//                Gdx.files.internal("shaders/red.fsh"));
        shader = new ShaderProgram(Gdx.files.internal("shaders/red.vsh"),
                Gdx.files.internal("shaders/electric.fsh"));
		System.out.println(shader.isCompiled() ? "Shader compiled" : shader.getLog());
		batch.setShader(shader);
	}

	float counter = 0;

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.7f, 0f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		counter += 0.02f;

//        Gdx.graphics.getGL20().glActiveTexture(GL20.GL_TEXTURE0);
//		img.bind(0);

		shader.begin();
//        shader.setUniformf("texture", 0);
		shader.setUniformf("u_time", counter);
        shader.setUniformf("u_resolution", new Vector2(img.getWidth(), img.getHeight()));
        shader.setUniformf("colorswap", 0.5f);
		shader.end();

		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		shader.dispose();
	}
}
