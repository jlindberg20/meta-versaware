# Purpose

This Yocto layer includes the customizations needed to build Yocto and B2Qt images
for Versaware hardware.

# Building Images

To get started, the follow the steps below, which are identical to https://variwiki.com/index.php?title=B2QT_Build_Release&release=RELEASE_DUNFELL_B2QT_V1.2_VAR-SOM-MX8M-NANO, with the addition of adding meta-variscite to conf/bblayers.conf:

**The very first build:**

```
$ mkdir -p ~/var-b2qt/sources/
$ cd ~/var-b2qt/sources/
$ git clone https://github.com/varigit/meta-variscite-boot2qt.git -b dunfell-var01
$ git clone https://github.com/nsdrude-varigit/meta-versaware.git -b dunfell
$ cd ..
$ ./sources/meta-variscite-boot2qt/b2qt-init-build-env init --device imx8mn-var-som
$ MACHINE=imx8mn-var-som source ./setup-environment.sh
```

Add meta-versaware to conf/bblayers.conf:

```
BBLAYERS ?= " \
  ${BSPDIR}/sources/poky/meta \
  ${BSPDIR}/sources/poky/meta-poky \
  ${BSPDIR}/sources/meta-freescale \
  ${BSPDIR}/sources/meta-freescale-3rdparty \
  ${BSPDIR}/sources/meta-variscite-fslc \
  ${BSPDIR}/sources/meta-versaware \
  ${BSPDIR}/sources/meta-openembedded/meta-oe \
  ${BSPDIR}/sources/meta-openembedded/meta-python \
  ${BSPDIR}/sources/meta-openembedded/meta-networking \
  ${BSPDIR}/sources/meta-openembedded/meta-initramfs \
  ${BSPDIR}/sources/meta-openembedded/meta-multimedia \
  ${BSPDIR}/sources/meta-boot2qt/meta-boot2qt \
  ${BSPDIR}/sources/meta-boot2qt/meta-boot2qt-distro \
  ${BSPDIR}/sources/meta-mingw \
  ${BSPDIR}/sources/meta-qt6 \
  "s
```

Finally, build the image:

```
$ MACHINE=imx8mn-var-som bitbake b2qt-embedded-qt6-image
```

**Future builds:**

When returning to build again in the future, you only need to follow these steps:

```
$ cd ~/var-b2qt/
$ MACHINE=imx8mn-var-som source ./setup-environment.sh
$ MACHINE=imx8mn-var-som bitbake b2qt-embedded-qt6-image
```

# Program image to a recovery sd card:

Follow the guide here to build a recovery sd card: https://variwiki.com/index.php?title=B2QT_Build_Release&release=RELEASE_DUNFELL_B2QT_V1.2_VAR-SOM-MX8M-NANO#Create_an_extended_SD_card

After booting this sd card, run '# install_yocto.sh' to install to eMMC.

# Updating the kernel

To update the Linux kernel, change the commit id, branch, and/or git repository in [recipes-kernel/linux/linux-variscite_5.4.bbappend](recipes-kernel/linux/linux-variscite_5.4.bbappend)
