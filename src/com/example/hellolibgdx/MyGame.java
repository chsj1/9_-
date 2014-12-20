package com.example.hellolibgdx;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class MyGame implements ApplicationListener {

	Texture animationTexture;
	TextureRegion temp[][];
	TextureRegion walkRegion[];
	TextureRegion keyFrameRegion;
	
	
	public static final int ROWS = 5;//���ͼƬ������
	public static final int COLS = 6;//���ͼƬ������
	
	SpriteBatch batch;
	
	
	Animation walkAnimation;
	
	float stateTime;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		
		animationTexture = new Texture(Gdx.files.internal("data/animation.png"));
		
		temp = TextureRegion.split(animationTexture, animationTexture.getWidth()/COLS, animationTexture.getHeight()/ROWS);
		
		walkRegion = new TextureRegion[ROWS*COLS];
		
		int i;
		int j;
		int index = 0;
		for(i = 0 ; i < ROWS ; ++i){
			for(j = 0 ; j < COLS ; ++j){
				walkRegion[index++] = temp[i][j];
			}
		}
		
		walkAnimation = new Animation(0.025f, walkRegion);
		/**
		 * normal:����ģʽ.��ͷ��β��һ��
		 * REVERSED:����ģʽ����β��ͷ����һ��
		 * LOOP:������ѭ����ʽ�����ϵش�ͷ��β��ѭ��
		 * LOOP_REVERSED: ������ѭ����ʽ.���ϵش�β��ͷ��ѭ��
		 * LOOP_PINGPONG: Ҳ�ǲ���ѭ��.��������ǰ����֡Ȼ����󲥼�֡
		 */
		walkAnimation.setPlayMode(walkAnimation.LOOP);//������������Ĳ���ģʽ
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}
	
	
	
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 1, 1, 1);// ���ñ���Ϊ��ɫ
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);// ����
		
		stateTime += Gdx.graphics.getDeltaTime();//��ȡһ��״̬�µĳ���ʱ��
		
//		keyFrameRegion = walkAnimation.getKeyFrame(stateTime, false);//��ȡ��ǰ֡
		keyFrameRegion = walkAnimation.getKeyFrame(stateTime, true);//��playModeΪLOOP_REVERSED��ʱ��,���ʱ����Ҫ���true
		
		batch.begin();
		
		batch.draw(keyFrameRegion,240,400);//�ѵ�ǰ֡������
		
		batch.end();
		
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

}
