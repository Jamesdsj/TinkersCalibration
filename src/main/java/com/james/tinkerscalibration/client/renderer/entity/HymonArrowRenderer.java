package com.james.tinkerscalibration.client.renderer.entity;

import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.projectile.AbstractArrow;

public class HymonArrowRenderer extends ArrowRenderer<AbstractArrow> {

    public static final ResourceLocation TEXTURE = new ResourceLocation("tinkerscalibration:textures/entity/projectiles/hymon_arrow.png");

    public HymonArrowRenderer(EntityRendererProvider.Context ctx) {

        super(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(AbstractArrow entity) {

        return TEXTURE;
    }
}
