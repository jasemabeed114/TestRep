package com.nicholaschirkevich.game.model.boosters;


import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.nicholaschirkevich.game.model.Prize;
import com.nicholaschirkevich.game.util.Constants;
import com.nicholaschirkevich.game.util.RandomUtil;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nikolas on 13.02.2016.
 */
public class DirtOnScreen extends Prize {
    private Random rand;
    private static World world;

    // public static final short PASSER_CAR_FILTER_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex
    //public static final short BLOCK_MASK = 0x11;    // 0001
    ; // 0010 or 0x2 in hex
    private int defaultX;
    private int defaultY;
    //public Body body;
    private int type = 0;

    private boolean isAlfa = false;
    private boolean isStartAlfa = false;
    private float alfaTime = 0;

    public float getAlfa() {
        return alfa;
    }

    public void setAlfa(float alfa) {
        this.alfa = alfa;
    }

    private float alfa = 1;
    private float stateTime;

    public DirtOnScreen(World world, int x, int y, int movement) {
        super(x, y, movement);
        defaultX = x;
        defaultY = y;
        rand = new Random();
        isLeft = rand.nextBoolean();
        type= RandomUtil.getRand(1, 5);
        position.x = RandomUtil.getRand(0,250);
        position.y = RandomUtil.getRand(0,500);
//        if (isLeft)
//            position.x = Constants.getCarPostitionXLeft(springboardAnimation.getKeyFrames()[0].getRegionWidth());
//        else
//            position.x = Constants.getCarPostitionXRight(springboardAnimation.getKeyFrames()[0].getRegionWidth());

        this.world = world;
        //sprite = new Sprite(carTexture);
        //sprite.setPosition(x, y);
        //passerCarAnimation = AssetsManager.getAnimation(animation_asset_id);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((springboardAnimation.getKeyFrames()[0].getRegionX() + springboardAnimation.getKeyFrames()[0].getRegionWidth() / 2)
                ,
                (springboardAnimation.getKeyFrames()[0].getRegionY() + springboardAnimation.getKeyFrames()[0].getRegionHeight() / 2));

       // body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(springboardAnimation.getKeyFrames()[0].getRegionWidth() / 2, springboardAnimation.getKeyFrames()[0].getRegionHeight()
                / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 0.5f;


//        fixtureDef.filter.categoryBits = BLOCK_MASK;
//        fixtureDef.filter.maskBits = MyCar.MY_CAR_FILTER_ENTITY;
//        body.setUserData(new SpringBoardDataType());
//        body.createFixture(fixtureDef);
        stateTime = 0f;

    }


    public DirtOnScreen(World world, int x, int y, int movement, boolean ifLeft) {
        super(x, y, movement);
        defaultX = x;
        defaultY = y;

        isLeft = ifLeft;
//        if (isLeft)
//            position.x = Constants.getCarPostitionXLeft(springboardAnimation.getKeyFrames()[0].getRegionWidth());
//        else
//            position.x = Constants.getCarPostitionXRight(springboardAnimation.getKeyFrames()[0].getRegionWidth());

        this.world = world;
        //sprite = new Sprite(carTexture);
        //sprite.setPosition(x, y);
        //passerCarAnimation = AssetsManager.getAnimation(animation_asset_id);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set((springboardAnimation.getKeyFrames()[0].getRegionX() + springboardAnimation.getKeyFrames()[0].getRegionWidth() / 2)
                ,
                (springboardAnimation.getKeyFrames()[0].getRegionY() + springboardAnimation.getKeyFrames()[0].getRegionHeight() / 2));

        //body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(springboardAnimation.getKeyFrames()[0].getRegionWidth() / 2, springboardAnimation.getKeyFrames()[0].getRegionHeight()
                / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 0.1f;
        fixtureDef.restitution = 0.5f;


//        fixtureDef.filter.categoryBits = BLOCK_MASK;
//        fixtureDef.filter.maskBits = MyCar.MY_CAR_FILTER_ENTITY;

       // body.createFixture(fixtureDef);
        stateTime = 0f;

    }

    public void draw(SpriteBatch batch) {
        Color color = batch.getColor();
        //batch.draw(getCoinShadowTexture(), position.x, position.y - 10);
        batch.setColor(color.r, color.g, color.b, alfa);
        switch (type) {
            case 1:
                batch.draw(getDirtOnScreen_1_Texture(), position.x, position.y);
                break;
            case 2:
                batch.draw(getDirtOnScreen_2_Texture(), position.x, position.y);
                break;
            case 3:
                batch.draw(getDirtOnScreen_3_Texture(), position.x, position.y);
                break;
            case 4:
                batch.draw(getDirtOnScreen_4_Texture(), position.x, position.y);
                break;
            case 5:
                batch.draw(getDirtOnScreen_5_Texture(), position.x, position.y);
                break;
        }
        batch.setColor(color.r, color.g, color.b, 1f);
        //batch.draw(getCoinShadowTexture(), position.x, position.y - 10);
       // batch.draw(getBlockTexture(), position.x, position.y);
    }

    @Override
    public void update(float dt) {
        //System.out.println("isStartAlfa"+isStartAlfa);
        if (isStartAlfa)
        {
            alfa-=0.02;
        }
       // position.add(0, (-GameManager.getCurrentSpeed()) * dt, 0);

        //sprite.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y);
       // body.setTransform(position.x, position.y, 0.0f);
        setPosition(position.x, position.y);
        stateTime += dt;
    }

    public void setDefaultPosition(int x) {
        position.set(0, x, 0);

        //sprite.setPosition(position.x, position.y);
        bounds.setPosition(position.x, position.y);
      //  body.setTransform(position.x, position.y, 0.0f);

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
        //body.setTransform(position.x, position.y, 0.0f);
    }

    @Override
    public void turn() {

    }

    public void dispose() {


    }


    public static void updateCoins(ArrayList<DirtOnScreen> busterses, Camera camera, float dt) {

        if (camera.viewportHeight - busterses.get(busterses.size() - 1).getPosition().y > Constants.coinDistance) {
//            boolean isBigCar = rand.nextBoolean();
//            if (!isBigCar)
            busterses.add(new DirtOnScreen(world, 90, (int) camera.viewportHeight + 20, 10));
//            else
//                passerCars.add(new PasserCar(world, 90, (int) camera.viewportHeight + 200, 10, true, Constants.OTHERCAR_2_1_ASSETS_ID));
//
        }

        for (int i = 0; i < busterses.size(); i++) {

            if (busterses.get(i).getPosition().y < 0) {
                //world.destroyBody(busterses.get(i).body);
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

    public boolean isStartAlfa() {
        return isStartAlfa;
    }

    public void setIsStartAlfa(boolean isStartAlfa) {
        this.isStartAlfa = isStartAlfa;
    }
}
