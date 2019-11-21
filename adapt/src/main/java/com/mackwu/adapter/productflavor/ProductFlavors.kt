package com.mackwu.adapter.productflavor

/**
 * ===================================================
 * Created by MackWu on 2019/11/19 17:13
 * <a href="mailto:wumengjiao828@163.com">Contact me</a>
 * <a href="https://github.com/mackwu828">Follow me</a>
 * ===================================================
 */

/**
 * product_id
 */
data class ProductId(val dev: Int = 0, val acc: Int = 0, val pro: Int = 0) // 默认0

/**
 * channel_id
 */
data class ChannelId(val dev: Int = 0, val acc: Int = 0, val pro: Int = 0) // 默认0

/**
 * brand_id
 */
data class BrandId(val dev: Int = 0, val acc: Int = dev, val pro: Int = dev) // 默认acc、pro的值与dev相同

/**
 * access_key
 */
data class AccessKey(val dev: String = "", val acc: String = dev, val pro: String = dev)  // 默认acc、pro的值与dev相同

/**
 * access_secret_key
 */
data class AccessSecretKey(val dev: String = "", val acc: String = dev, val pro: String = dev) // 默认acc、pro的值与dev相同

/**
 * ProductFlavor
 */
@Suppress("EnumEntryName")
enum class ProductFlavor(
        val productId: ProductId = ProductId(63, 40, 35), // 默认productId
        val channelId: ChannelId,
        val deviceType: String,
        val brandId: BrandId,
        val accessKey: AccessKey,
        val accessSecretKey: AccessSecretKey
) {

    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 单独渠道号 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    /**
     * 金锐显
     */
    Cultraview_338_cetus3(channelId = ChannelId(73, 69, 104), deviceType = "ro.product.devicetype", brandId = BrandId(19), accessKey = AccessKey("YA7MkRJYz9a5Rp6b53pl/g=="), accessSecretKey = AccessSecretKey("Hw06dOzCpXL/euCACr4XgEH+3q/X2+q7OXg9QxY0NoI=")),
    Cultraview_358_cetus1A(channelId = ChannelId(58, 54, 89), deviceType = "ro.product.devicetype", brandId = BrandId(19), accessKey = AccessKey("YA7MkRJYz9a5Rp6b53pl/g=="), accessSecretKey = AccessSecretKey("Hw06dOzCpXL/euCACr4XgEH+3q/X2+q7OXg9QxY0NoI=")),
    Cultraview_538_cetus3(channelId = ChannelId(56, 52, 87), deviceType = "ro.product.devicetype", brandId = BrandId(19), accessKey = AccessKey("YA7MkRJYz9a5Rp6b53pl/g=="), accessSecretKey = AccessSecretKey("Hw06dOzCpXL/euCACr4XgEH+3q/X2+q7OXg9QxY0NoI=")),
    Cultraview_920(channelId = ChannelId(87, 83, 118), deviceType = "ro.product.devicetype", brandId = BrandId(19), accessKey = AccessKey("YA7MkRJYz9a5Rp6b53pl/g=="), accessSecretKey = AccessSecretKey("Hw06dOzCpXL/euCACr4XgEH+3q/X2+q7OXg9QxY0NoI=")),
    Cultraview_920_cetus2(channelId = ChannelId(118, 114, 149), deviceType = "ro.product.devicetype", brandId = BrandId(19), accessKey = AccessKey("YA7MkRJYz9a5Rp6b53pl/g=="), accessSecretKey = AccessSecretKey("Hw06dOzCpXL/euCACr4XgEH+3q/X2+q7OXg9QxY0NoI=")),
    Cultraview_920_cetus3(channelId = ChannelId(107, 103, 138), deviceType = "ro.product.devicetype", brandId = BrandId(19), accessKey = AccessKey("YA7MkRJYz9a5Rp6b53pl/g=="), accessSecretKey = AccessSecretKey("Hw06dOzCpXL/euCACr4XgEH+3q/X2+q7OXg9QxY0NoI=")),
    Cultraview_950_cetus3(channelId = ChannelId(61, 57, 92), deviceType = "ro.product.devicetype", brandId = BrandId(19), accessKey = AccessKey("YA7MkRJYz9a5Rp6b53pl/g=="), accessSecretKey = AccessSecretKey("Hw06dOzCpXL/euCACr4XgEH+3q/X2+q7OXg9QxY0NoI=")),
    Cultraview_950X(channelId = ChannelId(47, 43, 78), deviceType = "ro.product.devicetype", brandId = BrandId(19), accessKey = AccessKey("YA7MkRJYz9a5Rp6b53pl/g=="), accessSecretKey = AccessSecretKey("Hw06dOzCpXL/euCACr4XgEH+3q/X2+q7OXg9QxY0NoI=")),

    /**
     * 海勤
     */
    HQ_CNC(channelId = ChannelId(59, 55, 90), deviceType = "ro.product.devicetype", brandId = BrandId(26, 23, 23), accessKey = AccessKey("DRAKR6T0fkdo7o1AFAXteA=="), accessSecretKey = AccessSecretKey("5GT9WrDVI7xIWrFvZgCMxTE6I3NodL966d0kTHzTdJo=")),
    HQ_PUBLIC(channelId = ChannelId(46, 42, 77), deviceType = "ro.product.devicetype", brandId = BrandId(26, 23, 23), accessKey = AccessKey("DRAKR6T0fkdo7o1AFAXteA=="), accessSecretKey = AccessSecretKey("5GT9WrDVI7xIWrFvZgCMxTE6I3NodL966d0kTHzTdJo=")),
    HQ_ASANO(channelId = ChannelId(59, 55, 69), deviceType = "ro.product.devicetype", brandId = BrandId(26, 23, 23), accessKey = AccessKey("DRAKR6T0fkdo7o1AFAXteA=="), accessSecretKey = AccessSecretKey("5GT9WrDVI7xIWrFvZgCMxTE6I3NodL966d0kTHzTdJo=")),
    HQ_Skyworth(channelId = ChannelId(49, 45, 80), deviceType = "ro.product.devicetype", brandId = BrandId(26, 23, 23), accessKey = AccessKey("DRAKR6T0fkdo7o1AFAXteA=="), accessSecretKey = AccessSecretKey("5GT9WrDVI7xIWrFvZgCMxTE6I3NodL966d0kTHzTdJo=")),
    HQ_MEXICO_CUSTOM(channelId = ChannelId(53, 49, 84), deviceType = "ro.product.devicetype", brandId = BrandId(26, 23, 23), accessKey = AccessKey("DRAKR6T0fkdo7o1AFAXteA=="), accessSecretKey = AccessSecretKey("5GT9WrDVI7xIWrFvZgCMxTE6I3NodL966d0kTHzTdJo=")),
    HQ_Haier(channelId = ChannelId(63, 59, 94), deviceType = "ro.product.devicetype", brandId = BrandId(26, 23, 23), accessKey = AccessKey("DRAKR6T0fkdo7o1AFAXteA=="), accessSecretKey = AccessSecretKey("5GT9WrDVI7xIWrFvZgCMxTE6I3NodL966d0kTHzTdJo=")),
    HQ_Custom(channelId = ChannelId(74, 70, 105), deviceType = "ro.product.devicetype", brandId = BrandId(26, 23, 23), accessKey = AccessKey("DRAKR6T0fkdo7o1AFAXteA=="), accessSecretKey = AccessSecretKey("5GT9WrDVI7xIWrFvZgCMxTE6I3NodL966d0kTHzTdJo=")),
    HQ_LIBERTON(channelId = ChannelId(105, 101, 136), deviceType = "ro.product.devicetype", brandId = BrandId(26, 23, 23), accessKey = AccessKey("DRAKR6T0fkdo7o1AFAXteA=="), accessSecretKey = AccessSecretKey("5GT9WrDVI7xIWrFvZgCMxTE6I3NodL966d0kTHzTdJo=")),
    HQ_Whaletv(channelId = ChannelId(89, 85, 120), deviceType = "ro.product.devicetype", brandId = BrandId(26, 23, 23), accessKey = AccessKey("DRAKR6T0fkdo7o1AFAXteA=="), accessSecretKey = AccessSecretKey("5GT9WrDVI7xIWrFvZgCMxTE6I3NodL966d0kTHzTdJo=")),
    HQ_HD(channelId = ChannelId(1, 1, 1), brandId = BrandId(26, 23, 23), deviceType = "ro.product.devicetype", accessKey = AccessKey("DRAKR6T0fkdo7o1AFAXteA=="), accessSecretKey = AccessSecretKey("5GT9WrDVI7xIWrFvZgCMxTE6I3NodL966d0kTHzTdJo=")),
    HQ_UKRAINE_61(channelId = ChannelId(1, 1, 1), brandId = BrandId(26, 23, 23), deviceType = "ro.product.devicetype", accessKey = AccessKey("DRAKR6T0fkdo7o1AFAXteA=="), accessSecretKey = AccessSecretKey("5GT9WrDVI7xIWrFvZgCMxTE6I3NodL966d0kTHzTdJo=")),

    /**
     * 鼎科
     */
    TOP_TECH(channelId = ChannelId(1, 1, 1), deviceType = "ro.devicetype.zeasn", brandId = BrandId(28, 25, 25), accessKey = AccessKey("NZPfE4LwzP6vhp63FxXenw=="), accessSecretKey = AccessSecretKey("vfRCGKLtOXinXPQwbDmTtmAqFoPHfcuHeNMcI1OAdGI=")),
    TOP_TECH_2831(channelId = ChannelId(114, 110, 145), deviceType = "ro.devicetype.zeasn", brandId = BrandId(28, 25, 25), accessKey = AccessKey("NZPfE4LwzP6vhp63FxXenw=="), accessSecretKey = AccessSecretKey("vfRCGKLtOXinXPQwbDmTtmAqFoPHfcuHeNMcI1OAdGI=")),
    TOP_TECH_2831_HYUNDAI(channelId = ChannelId(55, 51, 86), deviceType = "ro.devicetype.zeasn", brandId = BrandId(28, 25, 25), accessKey = AccessKey("NZPfE4LwzP6vhp63FxXenw=="), accessSecretKey = AccessSecretKey("vfRCGKLtOXinXPQwbDmTtmAqFoPHfcuHeNMcI1OAdGI=")),
    TOP_TECH_2841(channelId = ChannelId(52, 48, 83), deviceType = "ro.devicetype.zeasn", brandId = BrandId(28, 25, 25), accessKey = AccessKey("NZPfE4LwzP6vhp63FxXenw=="), accessSecretKey = AccessSecretKey("vfRCGKLtOXinXPQwbDmTtmAqFoPHfcuHeNMcI1OAdGI=")),
    TOP_TECH_2841_VIETNAM(channelId = ChannelId(78, 74, 109), deviceType = "ro.devicetype.zeasn", brandId = BrandId(28, 25, 25), accessKey = AccessKey("NZPfE4LwzP6vhp63FxXenw=="), accessSecretKey = AccessSecretKey("vfRCGKLtOXinXPQwbDmTtmAqFoPHfcuHeNMcI1OAdGI=")),
    TOP_TECH_2851(channelId = ChannelId(57, 53, 88), deviceType = "ro.devicetype.zeasn", brandId = BrandId(28, 25, 25), accessKey = AccessKey("NZPfE4LwzP6vhp63FxXenw=="), accessSecretKey = AccessSecretKey("vfRCGKLtOXinXPQwbDmTtmAqFoPHfcuHeNMcI1OAdGI=")),
    TOP_TECH_MSD638(channelId = ChannelId(83, 79, 114), deviceType = "ro.devicetype.zeasn", brandId = BrandId(28, 25, 25), accessKey = AccessKey("NZPfE4LwzP6vhp63FxXenw=="), accessSecretKey = AccessSecretKey("vfRCGKLtOXinXPQwbDmTtmAqFoPHfcuHeNMcI1OAdGI=")),

    /**
     * CVTE
     */
    CVTE_5510_Skyworth(channelId = ChannelId(120, 116, 151), deviceType = "ro.cvte.devicetype", brandId = BrandId(12), accessKey = AccessKey("JN/N/udZQJ5ycjXjtwh5oA=="), accessSecretKey = AccessSecretKey("pAZW4d4jo2Xd97Rz6TOkPjcwRBvzbVuPk6Igr0MlN7Q=")),
    CVTE_CAIXUN(channelId = ChannelId(45, 41, 76), deviceType = "ro.cvte.devicetype", brandId = BrandId(12), accessKey = AccessKey("JN/N/udZQJ5ycjXjtwh5oA=="), accessSecretKey = AccessSecretKey("pAZW4d4jo2Xd97Rz6TOkPjcwRBvzbVuPk6Igr0MlN7Q=")),
    CVTE_VTB_43(channelId = ChannelId(64, 60, 95), deviceType = "ro.cvte.devicetype", brandId = BrandId(12), accessKey = AccessKey("JN/N/udZQJ5ycjXjtwh5oA=="), accessSecretKey = AccessSecretKey("pAZW4d4jo2Xd97Rz6TOkPjcwRBvzbVuPk6Igr0MlN7Q=")),

    /**
     * KTC
     */
    KTC_Public(channelId = ChannelId(67, 63, 98), deviceType = "", brandId = BrandId(13), accessKey = AccessKey("1wS4NZb0371qcVH8gIv1SQ=="), accessSecretKey = AccessSecretKey("cywAgs+mL6Hgs4Yn06h+XqVlReSPWqMorCISi+ylyTE=")),
    KTC_Whaletv(channelId = ChannelId(91, 87, 122), deviceType = "", brandId = BrandId(13), accessKey = AccessKey("1wS4NZb0371qcVH8gIv1SQ=="), accessSecretKey = AccessSecretKey("cywAgs+mL6Hgs4Yn06h+XqVlReSPWqMorCISi+ylyTE=")),

    /**
     * 传音
     */
    Infinix_Hisi310(channelId = ChannelId(110, 106, 141), deviceType = "", brandId = BrandId(45, 43, 42), accessKey = AccessKey("w6wOd3gUtkkREJsZp5nEmw=="), accessSecretKey = AccessSecretKey("v0g7k/PQDB7+/rtnnpX2/r2uZguLniszyWdIVBGBnz4=")),
    Infinix_MSD538(channelId = ChannelId(109, 105, 140), deviceType = "", brandId = BrandId(45, 43, 42), accessKey = AccessKey("w6wOd3gUtkkREJsZp5nEmw=="), accessSecretKey = AccessSecretKey("v0g7k/PQDB7+/rtnnpX2/r2uZguLniszyWdIVBGBnz4=")),

    /**
     * 朗国
     */
    Lango_538_TW(channelId = ChannelId(65, 61, 96), deviceType = "ro.product.device", brandId = BrandId(29, 26, 26), accessKey = AccessKey("yCfrjgaDl+wmQTXkuOnN3g=="), accessSecretKey = AccessSecretKey("tOpRKrUvCVR+oH0jdak2y7Dki9A8b2uHoaNlVvPhbx4=")),

    /**
     * BOE
     */
    BOE_Public(channelId = ChannelId(75, 71, 106), deviceType = "ro.product.model", brandId = BrandId(46, 44, 43), accessKey = AccessKey("YIgKLbsyuG5fM+NkJYts6g=="), accessSecretKey = AccessSecretKey("+mppaej7cxtcEgNhd5HH5D4bQqqDPSQGW9Y5eZuAZzg=")),

    /**
     * 长虹
     */
    CH_ms6586Public(channelId = ChannelId(80, 76, 111), deviceType = "ro.build.firmwaretag", brandId = BrandId(40, 38, 37), accessKey = AccessKey("EjsC3yT4r4EOzydZzk2Nhw=="), accessSecretKey = AccessSecretKey("DK2AVAeaSFC7v5xfeWA1kYVv0f3sd+9/zbqd/YXlLDE=")),


    /**
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 统一渠道号 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     */
    // 志源
    ZhiYuan_msd358(channelId = ChannelId(48, 44, 79), deviceType = "mstar.zvt.devicetype", brandId = BrandId(8, 7, 7), accessKey = AccessKey("l46WACYSywgpyfV5Lb9Xbw=="), accessSecretKey = AccessSecretKey("0fSZUkb5/AWIEh4AAkxHjMYc+Qsmdam4+yM6M8vG3uU=")),
    // 屏丽
    LUXNPRO(channelId = ChannelId(51, 47, 82), deviceType = "ro.nextgenpro.devicetype", brandId = BrandId(8, 7, 7), accessKey = AccessKey("l46WACYSywgpyfV5Lb9Xbw=="), accessSecretKey = AccessSecretKey("0fSZUkb5/AWIEh4AAkxHjMYc+Qsmdam4+yM6M8vG3uU=")),
    // 泰科
    TECON_MS638(channelId = ChannelId(85, 81, 116), deviceType = "", brandId = BrandId(8, 7, 7), accessKey = AccessKey("l46WACYSywgpyfV5Lb9Xbw=="), accessSecretKey = AccessSecretKey("0fSZUkb5/AWIEh4AAkxHjMYc+Qsmdam4+yM6M8vG3uU=")),
    // 视景达
    VIDEO_SCAPE(channelId = ChannelId(94, 90, 125), deviceType = "ro.product.model", brandId = BrandId(8, 7, 7), accessKey = AccessKey("l46WACYSywgpyfV5Lb9Xbw=="), accessSecretKey = AccessSecretKey("0fSZUkb5/AWIEh4AAkxHjMYc+Qsmdam4+yM6M8vG3uU=")),
    // 新画面
    New_Picture_Msd358(channelId = ChannelId(104, 100, 135), deviceType = "ro.product.model", brandId = BrandId(8, 7, 7), accessKey = AccessKey("l46WACYSywgpyfV5Lb9Xbw=="), accessSecretKey = AccessSecretKey("0fSZUkb5/AWIEh4AAkxHjMYc+Qsmdam4+yM6M8vG3uU=")),
    // 视联
    Selenview_msd358(channelId = ChannelId(121, 117, 152), deviceType = "ro.selenview.devicetype", brandId = BrandId(8, 7, 7), accessKey = AccessKey("l46WACYSywgpyfV5Lb9Xbw=="), accessSecretKey = AccessSecretKey("0fSZUkb5/AWIEh4AAkxHjMYc+Qsmdam4+yM6M8vG3uU=")),
    // 战诚
    Kitking_msd358(channelId = ChannelId(123, 119, 154), deviceType = "ro.kitking.devicetype", brandId = BrandId(8, 7, 7), accessKey = AccessKey("l46WACYSywgpyfV5Lb9Xbw=="), accessSecretKey = AccessSecretKey("0fSZUkb5/AWIEh4AAkxHjMYc+Qsmdam4+yM6M8vG3uU=")),
    // SVT
    SVT_648_Contex(channelId = ChannelId(72, 68, 103), deviceType = "ro.product.devicetype", brandId = BrandId(8, 7, 7), accessKey = AccessKey("l46WACYSywgpyfV5Lb9Xbw=="), accessSecretKey = AccessSecretKey("0fSZUkb5/AWIEh4AAkxHjMYc+Qsmdam4+yM6M8vG3uU=")),
    // MTC
    MTC_Public(channelId = ChannelId(81, 77, 112), deviceType = "", brandId = BrandId(8, 7, 7), accessKey = AccessKey("l46WACYSywgpyfV5Lb9Xbw=="), accessSecretKey = AccessSecretKey("0fSZUkb5/AWIEh4AAkxHjMYc+Qsmdam4+yM6M8vG3uU=")),

}