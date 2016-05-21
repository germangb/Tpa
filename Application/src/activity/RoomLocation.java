package activity;

import activity.tasks.DelayTask;
import activity.tasks.DoSomethingTask;
import activity.tasks.Task;
import activity.tasks.TaskManager;
import game.Game;
import game.GameActivity;
import game.Values;
import rendering.*;
import rendering.materials.*;
import tpa.application.Context;
import tpa.audio.Music;
import tpa.audio.Sound;
import tpa.graphics.geometry.Mesh;
import tpa.graphics.texture.Texture;
import tpa.graphics.texture.TextureFilter;
import tpa.graphics.texture.TextureWrap;
import tpa.joml.Vector3f;

/**
 * Created by germangb on 13/04/16.
 */
public class RoomLocation extends LocationActivity {

    FpsInput fps = new FpsInput(camera);

    DecalActor door0, door1, door2;
    DecalActor notes3, notes4;
    DecalActor poster, poster1;
    DecalActor window0, window1;

    GeometryActor telf;
    GeometryActor wall, wallflip;
    GeometryActor wall1,wall1flip;
    GeometryActor pc;
    GeometryActor tile0, tile0flip;
    GeometryActor tile1, tile1flip;
    GeometryActor table;
    GeometryActor chair;
    GeometryActor alterego, alteregoHead;

    TextActor pcText, phoneText, alterText;

    Sound telfSound, hangPhone, pickupPhone, emailSound, dialSound, dialTonesSound, holsSound;
    Sound paper, laugh;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    TaskManager tasks = new TaskManager();

    boolean phoneActive = false;
    boolean musicPlaying = false;
    boolean walking = false;
    boolean notewallShowUp = false;
    boolean pointless_conversation = false;
    boolean bar_card = false;
    boolean alterShowUp = false;
    boolean perpetualPc = false;
    boolean friendface_monologue = false;
    boolean dial = false;
    boolean interrogation_room = false;
    boolean alterShowUp2 = false;
    boolean lover_house = false;
    boolean keepinvestigating = false;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onRoomPreLoad(Context context) {
        Game.getInstance().getResources().load("res/music/ambient.wav", Music.class);
        Game.getInstance().getResources().load("res/music/noise.wav", Music.class);
        Game.getInstance().getResources().load("res/sfx/telf0.wav", Sound.class);
        Game.getInstance().getResources().load("res/sfx/steps.wav", Sound.class);
        Game.getInstance().getResources().load("res/sfx/email.wav", Sound.class);
        Game.getInstance().getResources().load("res/sfx/hang_phone.wav", Sound.class);
        Game.getInstance().getResources().load("res/sfx/pickup_phone_16.wav", Sound.class);
        Game.getInstance().getResources().load("res/sfx/phone_hold.wav", Sound.class);
        Game.getInstance().getResources().load("res/sfx/paper_short_16.wav", Sound.class);
        Game.getInstance().getResources().load("res/sfx/dial.wav", Sound.class);
        Game.getInstance().getResources().load("res/sfx/dial_tones.wav", Sound.class);
        Game.getInstance().getResources().load("res/models/telf.json", Mesh.class);
        Game.getInstance().getResources().load("res/models/monkey.json", Mesh.class);
        Game.getInstance().getResources().load("res/models/room_tile.json", Mesh.class);
        Game.getInstance().getResources().load("res/models/wall_left.json", Mesh.class);
        Game.getInstance().getResources().load("res/models/table.json", Mesh.class);
        Game.getInstance().getResources().load("res/models/chair.json", Mesh.class);
        Game.getInstance().getResources().load("res/textures/room_texture.png", Texture.class);
        Game.getInstance().getResources().load("res/textures/room_texture_left.png", Texture.class);
        Game.getInstance().getResources().load("res/textures/door0.png", Texture.class);
        Game.getInstance().getResources().load("res/textures/door1.png", Texture.class);
        Game.getInstance().getResources().load("res/textures/door2.png", Texture.class);
        Game.getInstance().getResources().load("res/textures/table.png", Texture.class);
        Game.getInstance().getResources().load("res/textures/pc.png", Texture.class);
        Game.getInstance().getResources().load("res/textures/notes.png", Texture.class);
        Game.getInstance().getResources().load("res/textures/poster.png", Texture.class);
        Game.getInstance().getResources().load("res/textures/poster1.png", Texture.class);
        Game.getInstance().getResources().load("res/textures/telephone.png", Texture.class);
        Game.getInstance().getResources().load("res/textures/window0.png", Texture.class);
        Game.getInstance().getResources().load("res/models/pc.json", Mesh.class);
        Game.getInstance().getResources().load("res/models/capsule.json", Mesh.class);
        Game.getInstance().getResources().load("res/textures/pixel.png", Texture.class);
        Game.getInstance().getResources().load("res/sfx/crazy_laught.wav", Sound.class);

    }

    @Override
    public void onRoomPostLoad(Context context) {
        emailSound = Game.getInstance().getResources().get("res/sfx/email.wav", Sound.class);
        telfSound = Game.getInstance().getResources().get("res/sfx/telf0.wav", Sound.class);
        hangPhone = Game.getInstance().getResources().get("res/sfx/hang_phone.wav", Sound.class);
        pickupPhone = Game.getInstance().getResources().get("res/sfx/pickup_phone_16.wav", Sound.class);
        Sound steps = Game.getInstance().getResources().get("res/sfx/steps.wav", Sound.class);
        dialSound = Game.getInstance().getResources().get("res/sfx/dial.wav", Sound.class);
        dialTonesSound = Game.getInstance().getResources().get("res/sfx/dial_tones.wav", Sound.class);
        holsSound = Game.getInstance().getResources().get("res/sfx/phone_hold.wav", Sound.class);
        paper = Game.getInstance().getResources().get("res/sfx/paper_short_16.wav", Sound.class);
        laugh = Game.getInstance().getResources().get("res/sfx/crazy_laught.wav", Sound.class);

        fps.setSteps(steps);

        Mesh monkeyMesh = Game.getInstance().getResources().get("res/models/monkey.json", Mesh.class);
        Mesh telfModel = Game.getInstance().getResources().get("res/models/telf.json", Mesh.class);
        Mesh tileMesh = Game.getInstance().getResources().get("res/models/room_tile.json", Mesh.class);
        Mesh wallMesh = Game.getInstance().getResources().get("res/models/wall_left.json", Mesh.class);
        Mesh tableMesh = Game.getInstance().getResources().get("res/models/table.json", Mesh.class);
        Mesh chairMesh = Game.getInstance().getResources().get("res/models/chair.json", Mesh.class);
        Mesh pcMesh = Game.getInstance().getResources().get("res/models/pc.json", Mesh.class);
        Mesh capsuleModel = Game.getInstance().getResources().get("res/models/capsule.json", Mesh.class); //this is for characters
        Texture telfTex = Game.getInstance().getResources().get("res/textures/telephone.png", Texture.class);
        Texture tileTex = Game.getInstance().getResources().get("res/textures/room_texture.png", Texture.class);
        Texture doorTex = Game.getInstance().getResources().get("res/textures/door0.png", Texture.class);
        Texture door1Tex = Game.getInstance().getResources().get("res/textures/door1.png", Texture.class);
        Texture door2Tex = Game.getInstance().getResources().get("res/textures/door2.png", Texture.class);
        Texture tableTex = Game.getInstance().getResources().get("res/textures/table.png", Texture.class);
        Texture wallTex = Game.getInstance().getResources().get("res/textures/room_texture_left.png", Texture.class);
        Texture pcTex = Game.getInstance().getResources().get("res/textures/pc.png", Texture.class);
        Texture notesTex = Game.getInstance().getResources().get("res/textures/notes.png", Texture.class);
        Texture posterTex = Game.getInstance().getResources().get("res/textures/poster.png", Texture.class);
        Texture poster1Tex = Game.getInstance().getResources().get("res/textures/poster1.png", Texture.class);
        Texture windowTex = Game.getInstance().getResources().get("res/textures/window0.png", Texture.class);
        Texture capsuleTex = Game.getInstance().getResources().get("res/textures/pixel.png", Texture.class);

        // modify textures
        tileTex.setWrapU(TextureWrap.Repeat);
        tileTex.setWrapV(TextureWrap.Repeat);

        windowTex.setMin(TextureFilter.Linear);
        windowTex.setMag(TextureFilter.Linear);
        notesTex.setMin(TextureFilter.Linear);
        notesTex.setMag(TextureFilter.Linear);

        // create materials
        TexturedMaterial telfMat = new TexturedMaterial(telfTex);
        TexturedMaterial tileMat = new TexturedMaterial(tileTex);
        TexturedMaterial wallMat = new TexturedMaterial(wallTex);
        TexturedMaterial tableMat = new TexturedMaterial(telfTex);
        TexturedMaterial pcMat = new TexturedMaterial(pcTex);
        TexturedMaterial capsuleMat = new TexturedMaterial(capsuleTex);
        DecalMaterial doorMat = new DecalMaterial(doorTex, depth);
        DecalMaterial door1Mat = new DecalMaterial(door1Tex, depth);
        DecalMaterial door2Mat = new DecalMaterial(door2Tex, depth);
        DecalMaterial notesMat = new DecalMaterial(notesTex, depth);
        DecalMaterial posterMat = new DecalMaterial(posterTex, depth);
        DecalMaterial poster1Mat = new DecalMaterial(poster1Tex, depth);
        DecalMaterial windowMat = new DecalMaterial(windowTex, depth);


        // create everything and set its position
        telf = new GeometryActor(telfModel, telfMat);
        telf.position.set(2.25f, 1.0f, -2f);
        telf.update();
        door0 = new DecalActor(doorMat);
        door0.model.translate(1, 0, -1f).rotateY(56*3.14f/180).scale(0.85f, 0.1f, 0.85f);
        notes3 = new DecalActor(notesMat);
        notes4 = new DecalActor(notesMat);
        poster = new DecalActor(posterMat);
        poster1 = new DecalActor(poster1Mat);
        notes3.model.translate(2.25f + 0.5f, 1.1f, -2f).rotateX(90*3.1415f/180).rotateY(-90*3.1415f/180).scale(0.85f, 0.25f, 0.85f);
        notes4.model.translate(0, 1.1f, -1f).rotateZ(-90*3.1415f/180).scale(0.85f, 0.25f, 0.85f);
        poster.model.translate(2.25f + 3.5f, 1.1f, -2f).rotateZ(0.1f).rotateX(90*3.1415f/180).rotateY(-90*3.1415f/180).scale(0.75f, 0.05f, 0.75f);
        poster1.model.translate(2.25f + 4.75f, 1.0f, -2f).rotateZ(-0.1f).rotateX(90*3.1415f/180).rotateY(-90*3.1415f/180).scale(0.65f, 0.05f, 0.65f);
        door1 = new DecalActor(door1Mat);
        door1.model.translate(0, 0.85f + 1e-3f, -1).rotateY(180*3.1415f/180).rotateZ(90*3.1415f/180).scale(0.85f, 0.1f, 0.85f);
        door2 = new DecalActor(door2Mat);
        door2.model.translate(1, 0.85f + 1e-3f, -2).rotateY(90*3.1415f/180).rotateZ(90*3.1415f/180).scale(0.85f, 0.1f, 0.85f);
        wall = new GeometryActor(wallMesh, wallMat);
        wallflip = new GeometryActor(wallMesh, wallMat);
        wallflip.position.set(0, 0, 2);
        wallflip.update();
        wall1 = new GeometryActor(wallMesh, wallMat);
        wall1.model.translate(8, 0, -2).rotateY(180*3.1415f/180);
        wall1flip = new GeometryActor(wallMesh, wallMat);
        wall1flip.rotation.rotateY((float)Math.toRadians(180));
        wall1flip.position.set(8,0,0);
        wall1flip.update();
        pc = new GeometryActor(pcMesh, pcMat);
        pc.model.translate(3.9f, 0.675f, -1.5f).rotateY(0.25f);
        chair = new GeometryActor(chairMesh, pcMat);
        chair.model.translate(4f, 0, -0.55f).rotateY(0.25f).scale(0.85f);
        table = new GeometryActor(tableMesh, tableMat);
        table.model.translate(4f, 0, -2).rotateY(-0.1f);
        tile0 = new GeometryActor(tileMesh, tileMat);
        tile0flip = new GeometryActor(tileMesh, tileMat);
        tile0flip.rotation.rotateY((float)Math.toRadians(180));
        tile0flip.position.set(4, 0, 0);
        tile0flip.update();
        tile1flip = new GeometryActor(tileMesh, tileMat);
        tile1flip.rotation.rotateY((float)Math.toRadians(180));
        tile1flip.position.set(8, 0, 0);
        tile1flip.update();
        tile1 = new GeometryActor(tileMesh, tileMat);
        tile1.model.translate(4, 0, 0);

        window0 = new DecalActor(windowMat);
        window0.position.set(0, 1.1f, 0.75f);
        window0.rotation.rotateZ((float)Math.toRadians(-90));
        window0.scale.set(0.6f, 0.1f, 0.75f);
        window0.update();

        window1 = new DecalActor(windowMat);
        window1.position.set(8, 1.1f, -0.75f);
        window1.rotation.rotateZ((float)Math.toRadians(-90));
        window1.scale.set(0.6f, 0.1f, 0.75f);
        window1.update();

        capsuleMat.setTint(1,0,1);
        alterego = new GeometryActor(capsuleModel, capsuleMat);
        alterego.position.set(7.5f, 0f, 1.5f);
        alterego.scale.set(1, 0.5f, 1f);
        alterego.rotation.rotateY((float)Math.toRadians(-110));
        alterego.update();

        alteregoHead = new GeometryActor(monkeyMesh, capsuleMat);
        alteregoHead.position.set(7.5f, 0.5f, 1.5f);
        alteregoHead.scale.set(0.75f);
        alteregoHead.update();

        // set camera position
        fps.position.set(3, 1.25f, 0.5f);
        fps.setMovable(true);

        pcText = new TextActor("Laptop");
        pcText.position.set(3.9f, 0.675f, -1.5f).add(0, 0.5f, 0);
        pcText.update();

        phoneText = new TextActor("Telephone");
        phoneText.position.set(telf.position).add(0, 0.5f, 0.25f);
        phoneText.update();

        alterText = new TextActor("Sweetie");
        alterText.position.set(alterego.position).add(0,0.8f,0);
        alterText.update();
    }

    boolean asdasd = false;

    @Override
    public void onEntered(final Context context) {
        if (!asdasd) {
            tasks.clear();
            asdasd = false;
        }

        // play music
        if (!musicPlaying) {
            musicPlaying = true;
            Music music = Game.getInstance().getResources().get("res/music/ambient.wav", Music.class);
            context.audioRenderer.playMusic(music, true, 0);
        }

        if (alterShowUp) {
            Music noise = Game.getInstance().getResources().get("res/music/noise.wav", Music.class);
            context.audioRenderer.playMusic(noise, true, 1);
        }

        context.audioRenderer.setMusicGain(1, 0);

        // add actors to the scene
        addGeometry(telf);
        addGeometry(table);
        addGeometry(chair);
        addGeometry(pc);
        addGeometry(wall);
        addGeometry(wallflip);
        addGeometry(wall1);
        addGeometry(wall1flip);
        addGeometry(tile0);
        addGeometry(tile0flip);
        addGeometry(tile1flip);
        addGeometry(tile1);
        addDecal(window0);
        addDecal(window1);
        addDecal(door0);
        addDecal(door1);
        addDecal(door2);
        addDecal(notes3);
        addDecal(notes4);
        addDecal(poster);
        addDecal(poster1);
        addText(pcText);
        addText(phoneText);

        // set camera
        float aspect = (float) context.window.getWidth() / context.window.getHeight();
        camera.projection.setPerspective((float) Math.toRadians(60), aspect, 0.01f, 100f);
        camera.clearColor.set(0.0f);

        // You will receive a call
        if (Values.ARGUMENTO == 0) {
            // first wait 10 seconds
            tasks.add(new DelayTask(10, context.time));

            // after 10 seconds, add the clickable region and play the telf sound
            tasks.add(new DoSomethingTask(new Runnable() {
                @Override
                public void run() {
                    phoneActive = true;
                    RoomLocation.this.addPickerBox(new Vector3f(2.25f, 1.0f, -2f), new Vector3f(0.25f, 0.35f, 0.25f), "telf");
                    context.audioRenderer.playSound(telfSound, false);
                }
            }));
        }

        // you will receive an email
        if (Values.ARGUMENTO == 1) {
            if (perpetualPc) {
                addPickerBox(new Vector3f(3.9f, 1.0f, -1.5f), new Vector3f(0.35f, 0.25f, 0.35f), "pc");
            } else {
                // wait and then add click region for the email
                tasks.add(new DelayTask(5, context.time));
                tasks.add(new DoSomethingTask(new Runnable() {
                    @Override
                    public void run() {
                        RoomLocation.this.addPickerBox(new Vector3f(3.9f, 1.0f, -1.5f), new Vector3f(0.35f, 0.25f, 0.35f), "pc");
                        context.audioRenderer.playSound(emailSound, false);
                        perpetualPc = true;
                    }
                }));
            }
        }

        if (Values.ARGUMENTO == 2) {
            addPickerBox(new Vector3f(1, 1, -2), new Vector3f(0.5f, 1f, 0.1f), "leave");
        }

        if(Values.ARGUMENTO == 3 || Values.ARGUMENTO == 4){
            addPickerBox(new Vector3f(3.5f, 1.0f, -1.5f), new Vector3f(0.35f, 0.25f, 0.35f), "pc");
        }

        if (Values.ARGUMENTO == 5) {
            addPickerBox(new Vector3f(2.25f, 1.0f, -2f), new Vector3f(0.25f, 0.35f, 0.25f), "telf");
        }

        if(Values.ARGUMENTO == 8){
            alterShowUp2 = true;
            addPickerBox(new Vector3f(3.9f, 1.0f, -1.5f), new Vector3f(0.35f, 0.25f, 0.35f), "pc2");
        }

        if (Values.ARGUMENTO==20){
            addGeometry(alterego);
            addGeometry(alteregoHead);
            addText(alterText);
            if(!pointless_conversation){
                addPickerBox(new Vector3f(7.5f, 0.25f, 1.5f), new Vector3f(0.25f, 0.25f, 0.25f), "alter_ego_3");
            } else {
                addPickerBox(new Vector3f(7.5f, 0.25f, 1.5f), new Vector3f(0.25f, 0.25f, 0.25f), "alter_ego_pointless3");
            }
            if(!keepinvestigating) {
                addPickerBox(new Vector3f(3.9f, 1.0f, -1.5f), new Vector3f(0.35f, 0.25f, 0.35f), "pc3");
            }
        }

        if(Values.ARGUMENTO == 21){
            //this.setNoise((float)Math.random());
            //set projection to crazy mode
            camera.projection.setPerspective((float) Math.toRadians(140), aspect, 0.01f, 100f);
            addPickerBox(new Vector3f(3f, 1.0f, -2f), new Vector3f(0.7f, 0.7f, 0.1f), "goodend");
        }

        if(alterShowUp2){
            addGeometry(alterego);
            addGeometry(alteregoHead);
            addText(alterText);
            if(pointless_conversation){
                addPickerBox(new Vector3f(7.5f, 0.25f, 1.5f), new Vector3f(0.25f, 0.25f, 0.25f), "alter_ego_pointless2");
            }else{
                addPickerBox(new Vector3f(7.5f, 0.25f, 1.5f), new Vector3f(0.25f, 0.25f, 0.25f), "alter_ego2");
            }
        }

        // talk to the door
        addPickerBox(new Vector3f(1, 0, -1), new Vector3f(0.5f, 0.1f, 1), "fix_it");

        if(notewallShowUp){
            addPickerBox(new Vector3f(3f, 1.0f, -2f), new Vector3f(0.7f, 0.7f, 0.1f), "notes");
        }

        //talk to alter ego
        if (alterShowUp) {
            addText(alterText);
            addGeometry(alterego);
            addGeometry(alteregoHead);
            if (pointless_conversation) {
                addPickerBox(new Vector3f(7.5f, 0.25f, 1.5f), new Vector3f(0.25f, 0.25f, 0.25f), "alter_ego_pointless");
            } else {
                addPickerBox(new Vector3f(7.5f, 0.25f, 1.5f), new Vector3f(0.25f, 0.25f, 0.25f), "alter_ego");
            }
        }

        /*if(bar_card){
            bar_card = false;
            Game.getInstance().pushActivity(GameActivity.ReactionBarCard);
            Game.getInstance().pushActivity(GameActivity.BarCard);
        }*/

        /*if (friendface_monologue) {
            friendface_monologue = false;
            Game.getInstance().pushActivity(GameActivity.MonologueFriendface);
        }*/

        if(interrogation_room){
            addPickerBox(new Vector3f(1, 1, -2), new Vector3f(0.5f, 1f, 0.1f), "int_room");
            Values.ARGUMENTO = 7;
        }

        if(lover_house){
            addPickerBox(new Vector3f(1, 1, -2), new Vector3f(0.5f, 1f, 0.1f), "lover_room");
            Values.ARGUMENTO = 9;
        }

    }

    @Override
    public void onTick(Context context) {
        tasks.update();

        alteregoHead.rotation.identity().lookRotate(new Vector3f(fps.position).sub(alteregoHead.position).normalize(), new Vector3f(0, 1, 0)).invert();
        alteregoHead.update();

        pcText.billboard(camera);
        alterText.billboard(camera);
        phoneText.billboard(camera);

        // noise when you look at the alter ego
        if (alterShowUp) {
            Vector3f ref = new Vector3f(fps.position).sub(alterego.position);
            float len = ref.length();
            ref.normalize();
            Vector3f look = camera.look;
            float dot = Math.max(-look.dot(ref), 0);

            if (dot > 0.85) {
                float lenCont = Math.max(Math.min((len-3) * 0.5f, 1), 0);
                float di = (dot - 0.85f) / (1 - 0.85f) * lenCont;
                context.audioRenderer.setMusicGain(1, di*0.1f);
                setNoise(di * 0.25f);
            } else {
                setNoise(0);
                context.audioRenderer.setMusicGain(1, 0);
            }
        }


        // blinking phone
        TexturedMaterial telfMat = (TexturedMaterial) telf.getMaterial();
        if (phoneActive) {
            int tim = (int) (context.time.getTime() / 0.125f) % 2;
            float t = (float) Math.sin(context.time.getTime() * 64);
            telf.position.set(2.25f + t*0.0125f, 1.0f, -2f);
            telf.update();
            if (tim == 0) telfMat.setTint(1, 0.68f, 0.68f);
            else telfMat.setTint(1, 1, 1);
        } else {
            telfMat.setTint(1, 1, 1);
        }

        // update camera
        fps.update(context);
    }

    boolean zxczxc = false;

    @Override
    public void onSelected(final Context context, Object data) {
        if (zxczxc) return;
        if (data.equals("pc")) {
            //System.out.println("pc "+Values.ARGUMENTO);
            if (Values.ARGUMENTO == 1) {
                Game.getInstance().pushActivity(GameActivity.DialogueEmail, new ActivityListener() {
                    @Override
                    public void onResult(Activity act, Object data) {
                        //System.out.println(data);
                        if (data.equals("inbox")) {
                            bar_card = true;
                            Game.getInstance().popActivity();
                            Game.getInstance().pushActivity(GameActivity.ReactionBarCard);
                            Game.getInstance().pushActivity(GameActivity.BarCard);
                            Values.ARGUMENTO = 2;
                        }
                    }
                });
            } else if (Values.ARGUMENTO == 3) {
                //System.out.println("SEARCH");
                Game.getInstance().pushActivity(GameActivity.DialogueFriendface, new ActivityListener() {
                    @Override
                    public void onResult(Activity act, Object data) {
                        if (data.equals("search")) {
                            Game.getInstance().popActivity();
                            Game.getInstance().pushActivity(GameActivity.MonologueFriendface);
                            Game.getInstance().pushActivity(GameActivity.Friendface);
                            friendface_monologue = true;
                            Values.ARGUMENTO = 4;
                        }
                    }
                });
            } else if (Values.ARGUMENTO == 4) {
                Game.getInstance().pushActivity(GameActivity.DialogueToggle, new ActivityListener() {
                    @Override
                    public void onResult(Activity act, Object data) {
                        if (data.equals("search")) {
                            Game.getInstance().popActivity();
                            Game.getInstance().pushActivity(GameActivity.AnthonyReaction);
                            Game.getInstance().pushActivity(GameActivity.MapImage);
                            Values.ARGUMENTO = 5;
                        }
                    }
                });
            }

        } else if (data.equals("notes")) {
            context.audioRenderer.playSound(paper, false);
            Game.getInstance().pushActivity(GameActivity.Note0);
        } else if (data.equals("door")) {
            Game.getInstance().popActivity();
            Game.getInstance().pushActivity(GameActivity.Interrogation);
        } else if (data.equals("fix_it")) {
            Game.getInstance().pushActivity(GameActivity.FixDoor);
        } else if (data.equals("telf")) {
            if (Values.ARGUMENTO == 5) {
                Values.TEXT_COLOR = 0xFF3333;
                if (!dial) {
                    fps.setMovable(false);
                    dial = true;
                    context.audioRenderer.playSound(dialSound, false);
                    tasks.add(new DelayTask(4, context.time));
                    tasks.add(new DoSomethingTask(new Runnable() {
                        @Override
                        public void run() {
                            context.audioRenderer.playSound(dialTonesSound, false);
                        }
                    }));
                    tasks.add(new DelayTask(1, context.time));
                    tasks.add(new DoSomethingTask(new Runnable() {
                        @Override
                        public void run() {
                            context.audioRenderer.playSound(holsSound, false);
                        }
                    }));
                    tasks.add(new DelayTask(10, context.time));
                    tasks.add(new DoSomethingTask(new Runnable() {
                        @Override
                        public void run() {
                            Game.getInstance().pushActivity(GameActivity.WantToInterrogate, new ActivityListener() {
                                @Override
                                public void onResult(Activity act, Object data) {
                                    if (data.equals("finish")) {
                                        interrogation_room = true;
                                        dial = false;
                                        Values.ARGUMENTO = 6;
                                        fps.setMovable(true);
                                    }
                                }
                            });
                        }
                    }));
                }
            } else if (Values.ARGUMENTO == 0) {
                Values.TEXT_COLOR = 0xFF3333;
                Game.getInstance().pushActivity(GameActivity.DialoguePhone, new ActivityListener() {
                    @Override
                    public void onResult(Activity act, Object dat) {
                        if (dat.equals("finish")) {
                            alterShowUp = true;
                            Values.ARGUMENTO = 1;   // advance "plot counter"
                            context.audioRenderer.playSound(hangPhone, false);
                        } else if (dat.equals("screw_you")) {
                            phoneActive = false;
                            context.audioRenderer.playSound(hangPhone, false);
                        } else if (dat.equals("ignore")) {
                            phoneActive = false;
                            context.audioRenderer.playSound(hangPhone, false);
                            context.audioRenderer.stopSound(telfSound);
                        } else if (dat.equals("pickup")) {
                            phoneActive = false;
                            context.audioRenderer.stopSound(telfSound);
                            context.audioRenderer.playSound(pickupPhone, false);
                        }
                    }
                });
            }
        }  else if(data.equals("alter_ego")){
            Values.TEXT_COLOR = 0xff00ff;
            Game.getInstance().pushActivity(GameActivity.AlterEgo1, new ActivityListener() {
                @Override
                public void onResult(Activity act, Object dat) {
                    if (dat.equals("note_wall")) {
                        notewallShowUp = true;
                        pointless_conversation = true;
                    }
                }
            });
        } else if(data.equals("alter_ego_pointless")){
            Values.TEXT_COLOR = 0xff00ff;
            Game.getInstance().pushActivity(GameActivity.AlterEgoP);
            context.audioRenderer.playSound(laugh, false);
        } else if (data.equals("leave")) {
            Game.getInstance().popActivity();
            Game.getInstance().pushActivity(GameActivity.Club);
        }else if(data.equals("alter_ego2")){
            Values.TEXT_COLOR = 0xFF00FF;
            Game.getInstance().pushActivity(GameActivity.AlterEgo2, new ActivityListener() {
                @Override
                public void onResult(Activity act, Object dat) {
                    if (dat.equals("note2")) {
                        context.audioRenderer.playSound(paper, false);
                        pointless_conversation = true;
                        Game.getInstance().popActivity();
                        Game.getInstance().pushActivity(GameActivity.ReactionNote2);
                        Game.getInstance().pushActivity(GameActivity.Note1);
                    }
                }
            });

        }else if(data.equals("alter_ego_pointless2")){
            Values.TEXT_COLOR = 0xFF00FF;
            Game.getInstance().pushActivity(GameActivity.AlterEgoP2);
            context.audioRenderer.playSound(laugh, false);
        }else if(data.equals("pc2")){
            Game.getInstance().pushActivity(GameActivity.Terminal2, new ActivityListener() {
                @Override
                public void onResult(Activity act, Object data) {
                    if (data.equals("search_anthony")) {
                        Game.getInstance().popActivity();
                        Game.getInstance().pushActivity(GameActivity.MapImage);
                    }else if (data.equals("search_james")){
                        lover_house = true;
                        Game.getInstance().popActivity();
                        Game.getInstance().pushActivity(GameActivity.AfterJamesSearchDialogue);
                        Game.getInstance().pushActivity(GameActivity.JamesImage);
                    }else if(data.equals("search_jess")){
                        Game.getInstance().popActivity();
                        Game.getInstance().pushActivity(GameActivity.Friendface);
                    }
                }
            });
        }else if(data.equals("lover_room")){
            lover_house = false;
            Game.getInstance().popActivity();
            Game.getInstance().pushActivity(GameActivity.Acid);
        } else if(data.equals("int_room")){
            interrogation_room = false;
            Game.getInstance().popActivity();
            Game.getInstance().pushActivity(GameActivity.Interrogation);
        }else if (data.equals("alter_ego_3")){
            Values.TEXT_COLOR = 0xFF00FF;
            Game.getInstance().pushActivity(GameActivity.AlterEgo4);
            pointless_conversation=true;
        }else if (data.equals("alter_ego_pointless3")){
            Values.TEXT_COLOR = 0xFF00FF;
            Game.getInstance().pushActivity(GameActivity.AlterEgoRep4);
            context.audioRenderer.playSound(laugh, false);
        }else if(data.equals("pc3")){
            Game.getInstance().pushActivity(GameActivity.Terminal3, new ActivityListener() {
                @Override
                public void onResult(Activity act, Object data) {
                    if (data.equals("database")) {
                        Game.getInstance().popActivity();
                        Game.getInstance().pushActivity(GameActivity.LogWarning, new ActivityListener() {
                            @Override
                            public void onResult(Activity act2, Object dat) {
                                if (dat.equals("paul")) {
                                    Game.getInstance().popActivity();
                                    Game.getInstance().pushActivity(GameActivity.Ending1, new ActivityListener() {
                                        @Override
                                        public void onResult(Activity act3, Object dat3) {
                                            if (dat3.equals("yes")) {
                                                keepinvestigating = true;
                                                Game.getInstance().popActivity();
                                                Game.getInstance().popActivity();
                                                Game.getInstance().popActivity();
                                                Values.TEXT = "The End\n\nYou have reached the 2nd ending";
                                                Game.getInstance().pushActivity(GameActivity.Intro);
                                                Game.getInstance().pushActivity(GameActivity.NewspaperBad);
                                                context.audioRenderer.stopEverything();
                                            } else if (dat3.equals("no")) {
                                                Values.BAR_BIF |= 0x2;
                                                if (Values.BAR_BIF == 0x3) {
                                                    asdasd = true;
                                                    zxczxc = true;
                                                    // "good" ending
                                                    tasks.add(new Task() {
                                                        float t = 1;

                                                        @Override
                                                        public void onBegin() {
                                                            composite.setTimer(1);
                                                        }

                                                        @Override
                                                        public boolean onUpdate() {
                                                            t -= context.time.getFrameTime() * 0.25f;
                                                            composite.setTimer(Math.max(t, 0));
                                                            return t < 0;
                                                        }
                                                    });
                                                    tasks.add(new DoSomethingTask(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            Values.ARGUMENTO = 21;
                                                            Game.getInstance().popActivity();
                                                            Game.getInstance().pushActivity(GameActivity.Room);
                                                        }
                                                    }));
                                                } else {
                                                    asdasd = true;
                                                    zxczxc = true;
                                                    tasks.add(new Task() {
                                                        float t = 1;

                                                        @Override
                                                        public void onBegin() {
                                                            composite.setTimer(1);
                                                        }

                                                        @Override
                                                        public boolean onUpdate() {
                                                            t -= context.time.getFrameTime() * 0.25f;
                                                            composite.setTimer(Math.max(t, 0));
                                                            return t < 0;
                                                        }
                                                    });
                                                    tasks.add(new DoSomethingTask(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            // go to club
                                                            Game.getInstance().popActivity();
                                                            Game.getInstance().popActivity();
                                                            Game.getInstance().pushActivity(GameActivity.Club);
                                                            Values.ARGUMENTO = 19;
                                                        }
                                                    }));
                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }else if(data.equals("pacman")){
                        Game.getInstance().popActivity();
                        Game.getInstance().pushActivity(GameActivity.Pacman);
                    }else if(data.equals("email")){
                        Game.getInstance().popActivity();
                        Game.getInstance().pushActivity(GameActivity.EmailTB);
                    }
                }
            });
        }else if(data.equals("goodend")){
            Values.TEXT = "The End\n\nYou have reached the 3rd ending";
            Game.getInstance().popActivity();
            Game.getInstance().popActivity();
            Game.getInstance().popActivity();
            Game.getInstance().popActivity();
            Game.getInstance().pushActivity(GameActivity.Intro);
            Game.getInstance().pushActivity(GameActivity.NewspaperGood);
            Game.getInstance().pushActivity(GameActivity.ShortEnd);
            context.audioRenderer.stopEverything();
        }
    }

    @Override
    public void onLeft(Context context) {
        zxczxc = false;
    }
}
