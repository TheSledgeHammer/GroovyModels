/*
 * Copyright (c) 2017 SpaceToad and the BuildCraft team
 * This Source Code Form is subject to the terms of the Mozilla Public License, v. 2.0. If a copy of the MPL was not
 * distributed with this file, You can obtain one at https://mozilla.org/MPL/2.0/
 * Modified by TheSledgeHammer 2018: Converted to .groovy
 */
package com.thesledgehammer.groovymodels.client.model

import com.thesledgehammer.groovymc.utils.VecDouble
import com.thesledgehammer.groovymc.utils.VecLong
import net.minecraft.client.renderer.BufferBuilder
import net.minecraft.client.renderer.model.BakedQuad
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.Direction
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.Vec3i

import javax.vecmath.*

/** Holds all of the information necessary to make a {@link BakedQuad}. This provides a variety of methods to quickly
 * set or get different elements. This currently holds 4 {@link MutableVertex}. */
class MutableQuad {
    static final MutableQuad[] EMPTY_ARRAY = new MutableQuad[0];

    final MutableVertex vertex_0 = new MutableVertex();
    final MutableVertex vertex_1 = new MutableVertex();
    final MutableVertex vertex_2 = new MutableVertex();
    final MutableVertex vertex_3 = new MutableVertex();

    private int tintIndex = -1;
    private Direction face = null;
    private boolean shade = false;
    private TextureAtlasSprite sprite = null;

    MutableQuad() {}

    MutableQuad(int tintIndex, Direction face) {
        this(tintIndex, face, false);
    }

    MutableQuad(int tintIndex, Direction face, boolean shade) {
        this.tintIndex = tintIndex;
        this.face = face;
        this.shade = shade;
    }

    MutableQuad(MutableQuad from) {
        copyFrom(from);
    }

    MutableQuad copyFrom(MutableQuad from) {
        tintIndex = from.tintIndex;
        face = from.face;
        shade = from.shade;
        sprite = from.sprite;
        vertex_0.copyFrom(from.vertex_0);
        vertex_1.copyFrom(from.vertex_1);
        vertex_2.copyFrom(from.vertex_2);
        vertex_3.copyFrom(from.vertex_3);
        return this;
    }

    MutableQuad setTint(int tint) {
        tintIndex = tint;
        return this;
    }

    int getTint() {
        return tintIndex;
    }

    MutableQuad setFace(Direction face) {
        this.face = face;
        return this;
    }

    Direction getFace() {
        return face;
    }

    void setShade(boolean shade) {
        this.shade = shade;
    }

    boolean isShade() {
        return this.shade;
    }

    void setSprite(TextureAtlasSprite sprite) {
        this.sprite = sprite;
    }

    TextureAtlasSprite getSprite() {
        return this.sprite;
    }

    BakedQuad toBakedBlock() {
        int[] data = new int[28];
        vertex_0.toBakedBlock(data, 0);
        vertex_1.toBakedBlock(data, 7);
        vertex_2.toBakedBlock(data, 14);
        vertex_3.toBakedBlock(data, 21);
        return new BakedQuad(data, tintIndex, face, sprite, shade, DefaultVertexFormats.BLOCK);
    }

    BakedQuad toBakedItem() {
        int[] data = new int[28];
        vertex_0.toBakedItem(data, 0);
        vertex_1.toBakedItem(data, 7);
        vertex_2.toBakedItem(data, 14);
        vertex_3.toBakedItem(data, 21);
        return new BakedQuad(data, tintIndex, face, sprite, shade, DefaultVertexFormats.ITEM);
    }

    MutableQuad fromBakedBlock(BakedQuad quad) {
        tintIndex = quad.getTintIndex();
        face = quad.getFace();
        sprite = quad.getSprite();
        shade = quad.shouldApplyDiffuseLighting();

        int[] data = quad.getVertexData();
        int stride = (int) (data.length / 4);

        vertex_0.fromBakedBlock(data, 0);
        vertex_1.fromBakedBlock(data, stride);
        vertex_2.fromBakedBlock(data, stride * 2);
        vertex_3.fromBakedBlock(data, stride * 3);

        return this;
    }

    MutableQuad fromBakedItem(BakedQuad quad) {
        tintIndex = quad.getTintIndex();
        face = quad.getFace();
        sprite = quad.getSprite();
        shade = quad.shouldApplyDiffuseLighting();

        int[] data = quad.getVertexData();
        int stride = (int) (data.length / 4);

        vertex_0.fromBakedItem(data, 0);
        vertex_1.fromBakedItem(data, stride);
        vertex_2.fromBakedItem(data, stride * 2);
        vertex_3.fromBakedItem(data, stride * 3);

        return this;
    }

    void render(BufferBuilder bb) {
        vertex_0.render(bb);
        vertex_1.render(bb);
        vertex_2.render(bb);
        vertex_3.render(bb);
    }

    Vector3f getCalculatedNormal() {
        Vector3f a = new Vector3f(vertex_1.positionvf());
        a.sub(vertex_0.positionvf());

        Vector3f b = new Vector3f(vertex_2.positionvf());
        b.sub(vertex_0.positionvf());

        Vector3f c = new Vector3f();
        c.cross(a, b);
        return c;
    }

    void setCalculatedNormal() {
        normalvf(getCalculatedNormal());
    }

    static float diffuseLight(Vector3f normal) {
        return diffuseLight(normal.x, normal.y, normal.z);
    }

    static float diffuseLight(float x, float y, float z) {
        boolean up = y >= 0;

        float xx = (float) (x * x);
        float yy = (float) (y * y);
        float zz = (float) (z * z);

        float t = (float) (xx + yy + zz);
        float light = (float) ((xx * 0.6f + zz * 0.8f) / t);

        float yyt = yy / t;
        if (!up) yyt *= 0.5;
        light += yyt;

        return light;
    }

    float getCalculatedDiffuse() {
        return diffuseLight(getCalculatedNormal());
    }

    void setDiffuse(Vector3f normal) {
        float diffuse = diffuseLight(normal);
        colourf(diffuse, diffuse, diffuse, 1);
    }

    void setCalculatedDiffuse() {
        float diffuse = getCalculatedDiffuse();
        colourf(diffuse, diffuse, diffuse, 1);
    }

    /** Inverts a copy of this quad's normal so that it will render in the opposite direction. You will need to recall
     * diffusion calculations if you had previously calculated the diffuse. */
    MutableQuad copyAndInvertNormal() {
        MutableQuad copy = new MutableQuad(this);
        copy.vertex_0.copyFrom(vertex_3).invertNormal();
        copy.vertex_1.copyFrom(vertex_2).invertNormal();
        copy.vertex_2.copyFrom(vertex_1).invertNormal();
        copy.vertex_3.copyFrom(vertex_0).invertNormal();
        return copy;
    }

    MutableQuad rotateTextureUp(int times) {
        switch (times & 3) {
            case 0:
                return this;

            case 1:
                Point2f t = vertex_0.tex();
                vertex_0.texv(vertex_1.tex());
                vertex_1.texv(vertex_2.tex());
                vertex_2.texv(vertex_3.tex());
                vertex_3.texv(t);
                return this;

            case 2:
                Point2f t0 = vertex_0.tex();
                Point2f t1 = vertex_1.tex();
                vertex_0.texv(vertex_2.tex());
                vertex_1.texv(vertex_3.tex());
                vertex_2.texv(t0);
                vertex_3.texv(t1);
                return this;

            case 3:
                Point2f t = vertex_3.tex();
                vertex_3.texv(vertex_2.tex());
                vertex_2.texv(vertex_1.tex());
                vertex_1.texv(vertex_0.tex());
                vertex_0.texv(t);
                return this;

            default:
                throw new IllegalStateException("'times & 3' was not 0, 1, 2 or 3!");
        }
    }

    // ############################
    //
    // Delegate vertex functions
    //
    // Basically a lot of functions that
    // change every vertex in the same way
    //
    // ############################

    /* Position */

    // Note that you cannot set all of the position elements at once, so this is left empty

    /* Normal */

    /** Sets the normal for all vertices to the specified float coordinates. */
    MutableQuad normalf(float x, float y, float z) {
        vertex_0.normalf(x, y, z);
        vertex_1.normalf(x, y, z);
        vertex_2.normalf(x, y, z);
        vertex_3.normalf(x, y, z);
        return this;
    }

    /** Sets the normal for all vertices to the specified double coordinates. */
    MutableQuad normald(double x, double y, double z) {
        return normalf((float) x, (float) y, (float) z);
    }

    /** Sets the normal for all vertices to the specified {@link Vector3f}. */
    MutableQuad normalvf(Vector3f vec) {
        return normalf(vec.x, vec.y, vec.z);
    }

    /** Sets the normal for all vertices to the specified {@link Vec3d}. */
    MutableQuad normalvd(Vec3d vec) {
        return normald(vec.x, vec.y, vec.z);
    }

    /** Sets the normal for all vertices to the specified {@link com.thesledgehammer.groovymodels.utils.VecDouble}, using
     * {@link com.thesledgehammer.groovymodels.utils.VecDouble#a},{@link com.thesledgehammer.groovymodels.utils.VecDouble#b}, and {@link com.thesledgehammer.groovymodels.utils.VecDouble#c} */
    MutableQuad normalvd(VecDouble vec) {
        return normald(vec.a, vec.b, vec.c);
    }

    /** @return A new {@link Vector3f} with the normal of the first vertex. Only useful if the normal is expected to be
     *         the same for every vertex. */
    Vector3f normalvf() {
        return new Vector3f(vertex_0.normal_x, vertex_0.normal_y, vertex_0.normal_z);
    }

    /** @return A new {@link Vec3d} with the normal of the first vertex. Only useful if the normal is expected to be the
     *         same for every vertex. */
    Vec3d normalvd() {
        return new Vec3d(vertex_0.normal_x, vertex_0.normal_y, vertex_0.normal_z);
    }

    /* Colour */

    MutableQuad colouri(int r, int g, int b, int a) {
        vertex_0.colouri(r, g, b, a);
        vertex_1.colouri(r, g, b, a);
        vertex_2.colouri(r, g, b, a);
        vertex_3.colouri(r, g, b, a);
        return this;
    }

    MutableQuad colouri(int rgba) {
        vertex_0.colouri(rgba);
        vertex_1.colouri(rgba);
        vertex_2.colouri(rgba);
        vertex_3.colouri(rgba);
        return this;
    }

    MutableQuad colourf(float r, float g, float b, float a) {
        vertex_0.colourf(r, g, b, a);
        vertex_1.colourf(r, g, b, a);
        vertex_2.colourf(r, g, b, a);
        vertex_3.colourf(r, g, b, a);
        return this;
    }

    MutableQuad colourvl(VecLong vec) {
        return colouri((int) vec.a, (int) vec.b, (int) vec.c, (int) vec.d);
    }

    MutableQuad colourvf(Tuple4f vec) {
        return colourf(vec.x, vec.y, vec.z, vec.w);
    }

    MutableQuad multColourd(double r, double g, double b, double a) {
        vertex_0.multColourd(r, g, b, a);
        vertex_1.multColourd(r, g, b, a);
        vertex_2.multColourd(r, g, b, a);
        vertex_3.multColourd(r, g, b, a);
        return this;
    }

    MutableQuad multColourd(double by) {
        int m = (int) (by * 255);
        return multColouri(m);
    }

    MutableQuad multColouri(int by) {
        vertex_0.multColouri(by);
        vertex_1.multColouri(by);
        vertex_2.multColouri(by);
        vertex_3.multColouri(by);
        return this;
    }

    MutableQuad multColouri(int r, int g, int b, int a) {
        r &= 0xFF;
        g &= 0xFF;
        b &= 0xFF;
        a &= 0xFF;
        vertex_0.multColouri(r, g, b, a);
        vertex_1.multColouri(r, g, b, a);
        vertex_2.multColouri(r, g, b, a);
        vertex_3.multColouri(r, g, b, a);
        return this;
    }

    /** Multiplies every vertex by {@link #diffuseLight(float, float, float)} for the normal, if {@link #isShade()}
     * returns true. Also sets {@link #isShade()} to false. */
    MutableQuad multShade() {
        if (isShade()) {
            setShade(false);
            vertex_0.multShade();
            vertex_1.multShade();
            vertex_2.multShade();
            vertex_3.multShade();
        }
        return this;
    }

    /* Texture co-ords */

    MutableQuad texFromSprite(TextureAtlasSprite sprite) {
        vertex_0.texFromSprite(sprite);
        vertex_1.texFromSprite(sprite);
        vertex_2.texFromSprite(sprite);
        vertex_3.texFromSprite(sprite);
        return this;
    }

    /* Lightmap texture co-ords */

    MutableQuad lighti(int block, int sky) {
        vertex_0.lighti(block, sky);
        vertex_1.lighti(block, sky);
        vertex_2.lighti(block, sky);
        vertex_3.lighti(block, sky);
        return this;
    }

    MutableQuad lighti(int combined) {
        vertex_0.lighti(combined);
        vertex_1.lighti(combined);
        vertex_2.lighti(combined);
        vertex_3.lighti(combined);
        return this;
    }

    MutableQuad lightf(float block, float sky) {
        return lighti((int) (block * 15), (int) (sky * 15));
    }

    MutableQuad lightvf(Tuple2f vec) {
        return lightf(vec.x, vec.y);
    }

    /** Sets the current light value of every vertex to be the maximum of the given in value, and the current value */
    MutableQuad maxLighti(int block, int sky) {
        vertex_0.maxLighti(block, sky);
        vertex_1.maxLighti(block, sky);
        vertex_2.maxLighti(block, sky);
        vertex_3.maxLighti(block, sky);
        return this;
    }

    /* Transforms */

    MutableQuad transform(Matrix4f transformation) {
        vertex_0.transform(transformation);
        vertex_1.transform(transformation);
        vertex_2.transform(transformation);
        vertex_3.transform(transformation);
        return this;
    }

    MutableQuad translatei(int x, int y, int z) {
        return translatef(x, y, z);
    }

    MutableQuad translatef(float x, float y, float z) {
        vertex_0.translatef(x, y, z);
        vertex_1.translatef(x, y, z);
        vertex_2.translatef(x, y, z);
        vertex_3.translatef(x, y, z);
        return this;
    }

    MutableQuad translated(double x, double y, double z) {
        return translatef((float) x, (float) y, (float) z);
    }

    MutableQuad translatevi(Vec3i vec) {
        return translatei(vec.getX(), vec.getY(), vec.getZ());
    }

    MutableQuad translatevf(Vector3f vec) {
        return translatef(vec.x, vec.y, vec.z);
    }

    MutableQuad translatevd(Vec3d vec) {
        return translated(vec.x, vec.y, vec.z);
    }

    MutableQuad scalef(float scale) {
        vertex_0.scalef(scale);
        vertex_1.scalef(scale);
        vertex_2.scalef(scale);
        vertex_3.scalef(scale);
        return this;
    }

    MutableQuad scaled(double scale) {
        return scalef((float) scale);
    }

    MutableQuad scalef(float x, float y, float z) {
        vertex_0.scalef(x, y, z);
        vertex_1.scalef(x, y, z);
        vertex_2.scalef(x, y, z);
        vertex_3.scalef(x, y, z);
        return this;
    }

    MutableQuad scaled(double x, double y, double z) {
        return scalef((float) x, (float) y, (float) z);
    }

    void rotateX(float angle) {
        vertex_0.rotateX(angle);
        vertex_1.rotateX(angle);
        vertex_2.rotateX(angle);
        vertex_3.rotateX(angle);
    }

    void rotateY(float angle) {
        vertex_0.rotateY(angle);
        vertex_1.rotateY(angle);
        vertex_2.rotateY(angle);
        vertex_3.rotateY(angle);
    }

    void rotateZ(float angle) {
        vertex_0.rotateZ(angle);
        vertex_1.rotateZ(angle);
        vertex_2.rotateZ(angle);
        vertex_3.rotateZ(angle);
    }

    void rotateDirectlyX(float cos, float sin) {
        vertex_0.rotateDirectlyX(cos, sin);
        vertex_1.rotateDirectlyX(cos, sin);
        vertex_2.rotateDirectlyX(cos, sin);
        vertex_3.rotateDirectlyX(cos, sin);
    }

    void rotateDirectlyY(float cos, float sin) {
        vertex_0.rotateDirectlyY(cos, sin);
        vertex_1.rotateDirectlyY(cos, sin);
        vertex_2.rotateDirectlyY(cos, sin);
        vertex_3.rotateDirectlyY(cos, sin);
    }

    void rotateDirectlyZ(float cos, float sin) {
        vertex_0.rotateDirectlyZ(cos, sin);
        vertex_1.rotateDirectlyZ(cos, sin);
        vertex_2.rotateDirectlyZ(cos, sin);
        vertex_3.rotateDirectlyZ(cos, sin);
    }

    MutableQuad rotate(Direction from, Direction to, float ox, float oy, float oz) {
        if (from == to) {
            // don't bother rotating: there is nothing to rotate!
            return this;
        }

        translatef(-ox, -oy, -oz);
        // @formatter:off
        switch (from.getAxis()) {
            case X:
                int mult = from.getXOffset()
                switch (to.getAxis()) {
                    case X: rotateY_180(); break;
                    case Y: rotateZ_90(mult * to.getYOffset()); break;
                    case Z: rotateY_90(mult * to.getZOffset()); break;
                }
                break;

            case Y:
                int mult = from.getYOffset();
                switch (to.getAxis()) {
                    case X: rotateZ_90(-mult * to.getXOffset()); break;
                    case Y: rotateZ_180(); break;
                    case Z: rotateX_90(mult * to.getZOffset()); break;
                }
                break;

            case Z:
                int mult = -from.getZOffset();
                switch (to.getAxis()) {
                    case X: rotateY_90(mult * to.getXOffset()); break;
                    case Y: rotateX_90(mult * to.getYOffset()); break;
                    case Z: rotateY_180(); break;
                }
                break;

        }
        // @formatter:on
        translatef(ox, oy, oz);
        return this;
    }

    MutableQuad rotateX_90(float scale) {
        vertex_0.rotateX_90(scale);
        vertex_1.rotateX_90(scale);
        vertex_2.rotateX_90(scale);
        vertex_3.rotateX_90(scale);
        return this;
    }

    MutableQuad rotateY_90(float scale) {
        vertex_0.rotateY_90(scale);
        vertex_1.rotateY_90(scale);
        vertex_2.rotateY_90(scale);
        vertex_3.rotateY_90(scale);
        return this;
    }

    MutableQuad rotateZ_90(float scale) {
        vertex_0.rotateZ_90(scale);
        vertex_1.rotateZ_90(scale);
        vertex_2.rotateZ_90(scale);
        vertex_3.rotateZ_90(scale);
        return this;
    }

    MutableQuad rotateX_180() {
        vertex_0.rotateX_180();
        vertex_1.rotateX_180();
        vertex_2.rotateX_180();
        vertex_3.rotateX_180();
        return this;
    }

    MutableQuad rotateY_180() {
        vertex_0.rotateY_180();
        vertex_1.rotateY_180();
        vertex_2.rotateY_180();
        vertex_3.rotateY_180();
        return this;
    }

    MutableQuad rotateZ_180() {
        vertex_0.rotateZ_180();
        vertex_1.rotateZ_180();
        vertex_2.rotateZ_180();
        vertex_3.rotateZ_180();
        return this;
    }

    @Override
    String toString() {
        return "MutableQuad [vertices=" + vToS() + ", tintIndex=" + tintIndex + ", face=" + face + "]";
    }

    private String vToS() {
        return "[ " + vertex_0 + ", " + vertex_1 + ", " + vertex_2 + ", " + vertex_3 + " ]";
    }
}

