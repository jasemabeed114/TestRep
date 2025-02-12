package com.nicholaschirkevich.game.model.side_objects;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nicholaschirkevich.game.model.Prize;
import com.nicholaschirkevich.game.userdata.SpringBoardDataType;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.GameManager;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nikolas on 13.02.2016.
 */
public class Block extends Prize {
    private Random rand;
    private static World world;


    private int defaultX;
    private int defaultY;
    public Body body;


    private float stateTime;

    public Block(World world, int x, int y, int movement) {
        super(x, y, movement);
        defaultX = x;
        defaultY = y;
        rand = new Random();
        isLeft = rand.nextBoolean();


        this.world = world;


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((springboardAnimation.getKeyFrames()[0].getRegionX() + springboardAnimation.getKeyFrames()[0].getRegionWidth() / 2)
                ,
                (springboardAnimation.getKeyFrames()[0].getRegionY() + springboardAnimation.getKeyFrames()[0].getRegionHeight() / 2));

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(springboardAnimation.getKeyFrames()[0].getRegionWidth() / 2, springboardAnimation.getKeyFrames()[0].getRegionHeight()
                / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 1f;


        fixtureDef.filter.categoryBits = Constants.BLOCK_MASK;
        fixtureDef.filter.maskBits = Constants.MY_CAR_FILTER_ENTITY;
        body.setUserData(new SpringBoardDataType());
        body.createFixture(fixtureDef);
        stateTime = 0f;

    }


    public Block(World world, int x, int y, int movement, boolean ifLeft) {
        super(x, y, movement);
        defaultX = x;
        defaultY = y;

        isLeft = ifLeft;
//
        this.world = world;


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((springboardAnimation.getKeyFrames()[0].getRegionX() + springboardAnimation.getKeyFrames()[0].getRegionWidth() / 2)
                ,
                (springboardAnimation.getKeyFrames()[0].getRegionY() + springboardAnimation.getKeyFrames()[0].getRegionHeight() / 2));

        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(springboardAnimation.getKeyFrames()[0].getRegionWidth() / 2, springboardAnimation.getKeyFrames()[0].getRegionHeight()
                / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 1f;


        fixtureDef.filter.categoryBits = Constants.BLOCK_MASK;
        fixtureDef.filter.maskBits = Constants.MY_CAR_FILTER_ENTITY;

        body.createFixture(fixtureDef);
        stateTime = 0f;

    }

    public void draw(SpriteBatch batch) {

        batch.draw(getBlockTexture(), position.x, position.y);
    }

    @Override
    public void update(float dt) {

        position.add(0, (-GameManager.getCurrentSpeed()) * dt, 0);


        bounds.setPosition(position.x, position.y);
        body.setTransform(position.x, position.y, 0.0f);
        setPosition(position.x, position.y);
        stateTime += dt;
    }

    public void setDefaultPosition(int x) {
        position.set(0, x, 0);


        bounds.setPosition(position.x, position.y);
        body.setTransform(position.x, position.y, 0.0f);

    }


    public void setPosition() {

        int randomY = rand.nextInt((70 - 0) + 1) + 0;
        isLeft = rand.nextBoolean();
        if (isLeft)
            position.x = Constants.CAR_POS_X_LEFT - springboardAnimation.getKeyFrames()[0].getRegionHeight() / 2;
        else
            position.x = Constants.CAR_POS_X_RIGHT - springboardAnimation.getKeyFrames()[0].getRegionHeight() / 2;
        position.add(0, defaultY + randomY + 600, 0);
        //sprite.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y);
        body.setTransform(position.x, position.y, 0.0f);
    }

    @Override
    public void turn() {

    }

    public void dispose() {


    }


    public static void updateCoins(ArrayList<Block> busterses, Camera camera, float dt) {

        if (camera.viewportHeight - busterses.get(busterses.size() - 1).getPosition().y > Constants.coinDistance) {

            busterses.add(new Block(world, 90, (int) camera.viewportHeight + 20, 10));
//
        }

        for (int i = 0; i < busterses.size(); i++) {

            if (busterses.get(i).getPosition().y < 0) {
                world.destroyBody(busterses.get(i).body);
                busterses.remove(i);


            }
            busterses.get(i).update(dt);
        }

    }


    public float getStateTime() {
        return stateTime;
    }

    public Animation getCoinAnimation() {
        return coinAnimation;
    }
}
