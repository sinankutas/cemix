package com.arneca.evyap.api.response.cmx;/*
 * Created by Sinan KUTAS on 8/15/22.
 */

import java.util.List;

public class OpenDocumentListResponse {


    /**
     * result : [{"belge_id":142,"guid":"4f0ea2e4-3ea4-407a-b5da-a091be005ee0","seri":"satis_d14","sira":103,"tarih":"15.08.2022 10:47:23","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":141,"guid":"e6c610c9-e5e5-4597-a4b7-52f15b4f6893","seri":"satis_d14","sira":102,"tarih":"09.08.2022 20:09:34","detay_kayit_sayisi":3,"detay_stok_miktari":"36"},{"belge_id":140,"guid":"8fdcf90e-cafc-4033-8cf8-6cfa900e9e2a","seri":"satis_d14","sira":101,"tarih":"09.08.2022 20:08:53","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":136,"guid":"abb7e5b1-f5e9-43e6-ac63-ffd019f180f6","seri":"satis_d14","sira":97,"tarih":"09.08.2022 09:58:03","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":135,"guid":"cf687f3d-38b5-402c-80fc-d625fc98ec27","seri":"satis_d14","sira":96,"tarih":"09.08.2022 09:58:01","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":134,"guid":"ef53900b-07fc-4068-b7cb-9837a27253af","seri":"satis_d14","sira":95,"tarih":"09.08.2022 09:55:13","detay_kayit_sayisi":1,"detay_stok_miktari":"10"},{"belge_id":133,"guid":"a6d2614e-a6e2-4eb9-a229-74b63f44f1e2","seri":"satis_d14","sira":94,"tarih":"09.08.2022 09:42:02","detay_kayit_sayisi":1,"detay_stok_miktari":"10"},{"belge_id":132,"guid":"70b263ae-d431-4f29-bab7-1fbc535931cd","seri":"satis_d14","sira":93,"tarih":"09.08.2022 09:31:57","detay_kayit_sayisi":1,"detay_stok_miktari":"10"},{"belge_id":131,"guid":"c867ae88-673a-4d4a-be39-15ed0e0ab259","seri":"satis_d14","sira":92,"tarih":"09.08.2022 09:31:55","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":130,"guid":"c673cc7e-a20a-4b2c-be88-0f3f0462acec","seri":"satis_d14","sira":91,"tarih":"09.08.2022 09:31:51","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":129,"guid":"32ae1fb6-777a-4b00-8c48-e4019ed08933","seri":"satis_d14","sira":90,"tarih":"09.08.2022 09:27:29","detay_kayit_sayisi":3,"detay_stok_miktari":"60"},{"belge_id":128,"guid":"e40d5083-e024-4373-8a1a-87f39396d758","seri":"satis_d14","sira":89,"tarih":"09.08.2022 09:27:25","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":127,"guid":"549a6a91-6c9f-41e5-916b-b6a703793d5e","seri":"satis_d14","sira":88,"tarih":"09.08.2022 09:27:21","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":126,"guid":"550f9077-0365-4812-8694-f0404dbafe75","seri":"satis_d14","sira":87,"tarih":"09.08.2022 09:27:16","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":125,"guid":"5c932de5-c74b-430d-9b04-9e8271c188c0","seri":"satis_d14","sira":86,"tarih":"09.08.2022 09:23:26","detay_kayit_sayisi":4,"detay_stok_miktari":"150"},{"belge_id":124,"guid":"7af9c214-151f-4dc2-b114-895a4f8f593e","seri":"satis_d14","sira":85,"tarih":"09.08.2022 09:21:21","detay_kayit_sayisi":3,"detay_stok_miktari":"17"},{"belge_id":123,"guid":"79f950e1-0aca-434e-b1e3-30adeac43ea4","seri":"satis_d14","sira":84,"tarih":"09.08.2022 09:21:19","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":122,"guid":"1b3c4fa5-7dfd-4273-8ffa-f32493438358","seri":"satis_d14","sira":83,"tarih":"09.08.2022 09:21:13","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":121,"guid":"26505f4e-55db-4696-b972-d030fa46a83d","seri":"satis_d14","sira":82,"tarih":"08.08.2022 11:37:20","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":120,"guid":"81027ef4-d1cf-44a8-844d-57ffada56a8c","seri":"satis_d14","sira":81,"tarih":"08.08.2022 11:36:38","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":119,"guid":"d2342362-9467-4224-9342-9262fbc2dbaf","seri":"satis_d14","sira":80,"tarih":"08.08.2022 11:36:37","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":118,"guid":"ea4518e7-9140-4514-9c10-5f1540925fdb","seri":"satis_d14","sira":79,"tarih":"08.08.2022 11:34:00","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":117,"guid":"f3934ed3-9249-4f97-9694-adfa2c587f28","seri":"satis_d14","sira":78,"tarih":"08.08.2022 11:33:59","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":116,"guid":"8f434fca-4db7-419f-9885-bdaa7f655af2","seri":"satis_d14","sira":77,"tarih":"08.08.2022 11:33:38","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":115,"guid":"61d02dc0-bc69-4227-99b7-d9ba65ff9593","seri":"satis_d14","sira":76,"tarih":"08.08.2022 11:33:37","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":114,"guid":"8ec05ce1-3d40-4771-8eb7-e49736250121","seri":"satis_d14","sira":75,"tarih":"08.08.2022 11:33:33","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":113,"guid":"cdcf7628-022c-4f8a-af5c-a30a544bb69a","seri":"satis_d14","sira":74,"tarih":"08.08.2022 11:27:28","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":112,"guid":"dc77cc9a-05ec-47d4-8fd8-de7a1eb2f2d0","seri":"satis_d14","sira":73,"tarih":"08.08.2022 11:26:28","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":111,"guid":"02a2922e-c118-4df3-b8ed-7b3b6dd261b6","seri":"satis_d14","sira":72,"tarih":"08.08.2022 11:26:24","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":110,"guid":"a2b7bc85-9e87-42f4-8a11-ed5b2af50312","seri":"satis_d14","sira":71,"tarih":"08.08.2022 11:25:46","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":109,"guid":"eed94b47-ec84-4084-ae8e-9c280ad6c6f3","seri":"satis_d14","sira":70,"tarih":"08.08.2022 11:25:44","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":108,"guid":"7fa75784-859c-4157-ace4-032e5bbcd91b","seri":"satis_d14","sira":69,"tarih":"08.08.2022 11:25:41","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":107,"guid":"a7cdd7b3-a759-4cb1-9365-0e6f58903553","seri":"satis_d14","sira":68,"tarih":"08.08.2022 11:25:39","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":106,"guid":"a1c3ffcc-2142-4c73-9463-c384ed596f47","seri":"satis_d14","sira":67,"tarih":"08.08.2022 11:25:36","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":105,"guid":"e8c95e9f-c367-40f3-a633-4eadc481f814","seri":"satis_d14","sira":66,"tarih":"08.08.2022 11:16:41","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":104,"guid":"87f284d9-321e-4352-bb3d-1b943748bb7e","seri":"satis_d14","sira":65,"tarih":"08.08.2022 11:11:43","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":103,"guid":"02fd006c-f7b5-423d-a6c1-997b9e5cb38b","seri":"satis_d14","sira":64,"tarih":"08.08.2022 11:08:35","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":102,"guid":"3e76786c-4c04-46bf-9094-f9b120dfa6e7","seri":"satis_d14","sira":63,"tarih":"08.08.2022 11:03:37","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":101,"guid":"d4d9d9f8-d16d-4bb9-9730-764dfc90c9f6","seri":"satis_d14","sira":62,"tarih":"08.08.2022 10:56:44","detay_kayit_sayisi":1,"detay_stok_miktari":"2"},{"belge_id":100,"guid":"20f2a5ba-de44-4e7b-845e-f3e1718eb45b","seri":"satis_d14","sira":61,"tarih":"08.08.2022 10:44:28","detay_kayit_sayisi":17,"detay_stok_miktari":"88"},{"belge_id":99,"guid":"a398ce5f-648d-4048-b7b9-c6502a980d30","seri":"satis_d14","sira":60,"tarih":"08.08.2022 10:39:31","detay_kayit_sayisi":6,"detay_stok_miktari":"16"},{"belge_id":98,"guid":"6ad36349-4cad-4ad5-8109-055a31fb26ce","seri":"satis_d14","sira":59,"tarih":"08.08.2022 10:37:37","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":97,"guid":"64aa72b6-1ef7-476d-bc29-065129944b24","seri":"satis_d14","sira":58,"tarih":"08.08.2022 09:58:41","detay_kayit_sayisi":1,"detay_stok_miktari":"2"},{"belge_id":96,"guid":"02adfa75-44da-4953-b88f-13971855ec31","seri":"satis_d14","sira":57,"tarih":"08.08.2022 09:51:42","detay_kayit_sayisi":1,"detay_stok_miktari":"3"},{"belge_id":95,"guid":"83047398-d44d-4e15-bb60-658a29d479a9","seri":"satis_d14","sira":56,"tarih":"08.08.2022 09:30:35","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":94,"guid":"970d294f-ff1e-45fd-bfd9-f71aaecb43f1","seri":"satis_d14","sira":55,"tarih":"08.08.2022 09:30:29","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":93,"guid":"40c36404-fd2d-4dcc-a97a-187c32fb58d0","seri":"satis_d14","sira":54,"tarih":"08.08.2022 09:29:32","detay_kayit_sayisi":1,"detay_stok_miktari":"3"},{"belge_id":92,"guid":"5ea795bc-fb84-4312-a021-53238f2c10ec","seri":"satis_d14","sira":53,"tarih":"08.08.2022 09:18:01","detay_kayit_sayisi":5,"detay_stok_miktari":"10"},{"belge_id":91,"guid":"a34daa01-2692-43c7-80cf-f9a038d97516","seri":"satis_d14","sira":52,"tarih":"08.08.2022 09:10:24","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":90,"guid":"258e644d-0d02-495c-b997-8b35003284fe","seri":"satis_d14","sira":51,"tarih":"08.08.2022 09:08:36","detay_kayit_sayisi":4,"detay_stok_miktari":"10"},{"belge_id":89,"guid":"df272f2b-7382-45f5-a140-09eca718411e","seri":"satis_d14","sira":50,"tarih":"08.08.2022 09:01:47","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":88,"guid":"4ba89c61-fc72-450c-a8cd-8e4601d6b3b7","seri":"satis_d14","sira":49,"tarih":"07.08.2022 13:42:20","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":87,"guid":"b3693662-26b9-43cb-b087-b0a8550ad607","seri":"satis_d14","sira":48,"tarih":"07.08.2022 08:40:05","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":86,"guid":"bf55c9f1-36dc-40cd-9e3c-18a80fa92b9c","seri":"satis_d14","sira":47,"tarih":"07.08.2022 08:38:16","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":84,"guid":"89ea5167-8c36-4834-a05a-03a6b6451aa2","seri":"satis_d14","sira":45,"tarih":"06.08.2022 19:31:14","detay_kayit_sayisi":2,"detay_stok_miktari":"10"},{"belge_id":83,"guid":"62b04091-2408-4bfb-bf30-d2b5689a8015","seri":"satis_d14","sira":44,"tarih":"06.08.2022 19:05:34","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":82,"guid":"72d4be4d-b198-44d4-b486-0d6372a3b1e6","seri":"satis_d14","sira":43,"tarih":"06.08.2022 18:57:38","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":80,"guid":"ce50b76b-41f9-42dd-8445-28e19bfdab54","seri":"satis_d14","sira":41,"tarih":"06.08.2022 18:48:06","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":79,"guid":"367f2b8c-3b85-4eb7-a91b-354562f0186d","seri":"satis_d14","sira":40,"tarih":"06.08.2022 18:44:51","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":78,"guid":"fc3e3322-c3a4-4b6c-b59d-7d83fc361a82","seri":"satis_d14","sira":39,"tarih":"06.08.2022 18:38:19","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":77,"guid":"8fe2cb93-f610-4e0e-93aa-0b883d7cdce6","seri":"satis_d14","sira":38,"tarih":"06.08.2022 18:27:13","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":76,"guid":"e9914861-b7df-4cf3-883f-a77837fd8085","seri":"satis_d14","sira":37,"tarih":"06.08.2022 18:20:30","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":75,"guid":"eab528ea-e478-436d-aafe-7175db6c7085","seri":"satis_d14","sira":36,"tarih":"06.08.2022 17:39:18","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":74,"guid":"17518b92-148e-43f9-9fbc-0cc5cf1314b2","seri":"satis_d14","sira":35,"tarih":"06.08.2022 17:38:08","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":73,"guid":"10a729a1-40e7-4cac-8563-3a40e54ba4bb","seri":"satis_d14","sira":34,"tarih":"06.08.2022 17:33:29","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":72,"guid":"404f57ad-dd45-457e-951c-d2eb657f14db","seri":"satis_d14","sira":33,"tarih":"06.08.2022 17:31:45","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":71,"guid":"c064aa62-960a-4b19-8fc4-733bc088884e","seri":"satis_d14","sira":32,"tarih":"06.08.2022 17:30:22","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":70,"guid":"05fb235b-aca3-453f-9966-11bb925f2f5d","seri":"satis_d14","sira":31,"tarih":"06.08.2022 17:27:36","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":69,"guid":"1fc651a7-f80d-4b02-a8ca-045e0a4e8c52","seri":"satis_d14","sira":30,"tarih":"06.08.2022 17:19:10","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":68,"guid":"1401ce66-a45c-4edb-bbdf-1a74dd5112f7","seri":"satis_d14","sira":29,"tarih":"06.08.2022 13:55:48","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":67,"guid":"24ec5ded-866a-4521-ae43-a91749c7d360","seri":"satis_d14","sira":28,"tarih":"06.08.2022 13:55:20","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":66,"guid":"7f6da13e-4d13-4d70-b22f-721439d59756","seri":"satis_d14","sira":27,"tarih":"06.08.2022 13:54:33","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":65,"guid":"b231415b-8fd5-4495-a280-e6d297b42aba","seri":"satis_d14","sira":26,"tarih":"06.08.2022 13:49:56","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":64,"guid":"846c8841-c66a-4788-8740-26b3788d98d8","seri":"satis_d14","sira":25,"tarih":"06.08.2022 13:48:41","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":63,"guid":"9ea6b3cd-d2d9-4831-acab-9077f1954861","seri":"satis_d14","sira":24,"tarih":"06.08.2022 13:41:57","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":62,"guid":"5671045c-12d9-4294-a849-4a5b59621b45","seri":"satis_d14","sira":23,"tarih":"06.08.2022 13:36:59","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":61,"guid":"aec979f8-54c5-4b52-bc44-4a9503804c8c","seri":"satis_d14","sira":22,"tarih":"06.08.2022 13:21:48","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":60,"guid":"abbe54c2-7d74-4932-b8bf-c59862bfc914","seri":"satis_d14","sira":21,"tarih":"06.08.2022 13:18:03","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":59,"guid":"5e2d7dad-0297-4298-b0bb-cd266a537bbc","seri":"satis_d14","sira":20,"tarih":"05.08.2022 23:25:47","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":58,"guid":"d8d6190d-f498-4f41-92ee-402438cbcdcf","seri":"satis_d14","sira":19,"tarih":"05.08.2022 23:23:24","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":57,"guid":"f6258538-210f-4b53-ab2f-54f4c0772f1f","seri":"satis_d14","sira":18,"tarih":"05.08.2022 23:22:40","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":56,"guid":"6313e22c-62c1-404b-86f3-e53838cf4de1","seri":"satis_d14","sira":17,"tarih":"05.08.2022 23:13:05","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":55,"guid":"db7ddb71-0782-4611-9e0f-8512e68d20ea","seri":"satis_d14","sira":16,"tarih":"05.08.2022 23:03:51","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":54,"guid":"5ef16591-0bdf-4d4e-8626-9c03bc124db4","seri":"satis_d14","sira":15,"tarih":"05.08.2022 22:40:03","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":53,"guid":"e8968dbd-8ed9-4768-a8f9-25497297af3b","seri":"satis_d14","sira":14,"tarih":"05.08.2022 22:39:41","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":52,"guid":"3b4775d7-7b93-4fd5-8595-ba939b3cde82","seri":"satis_d14","sira":13,"tarih":"05.08.2022 20:57:07","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":51,"guid":"635bdefc-a1f3-48db-a36e-d50c826e5053","seri":"satis_d14","sira":12,"tarih":"05.08.2022 20:56:35","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":50,"guid":"27879b99-d249-401c-909f-bc115050ece5","seri":"satis_d14","sira":11,"tarih":"05.08.2022 20:54:35","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":49,"guid":"43fa0641-6ecb-4c34-86b7-d3f8c24fc707","seri":"satis_d14","sira":10,"tarih":"05.08.2022 20:53:57","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":48,"guid":"e57ea234-a1d6-4921-875c-d6ddb013ffbb","seri":"satis_d14","sira":9,"tarih":"05.08.2022 20:51:59","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":47,"guid":"4c2a806a-4915-451b-8467-4cd0c8a9b5f1","seri":"satis_d14","sira":8,"tarih":"05.08.2022 20:51:50","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":46,"guid":"ef682c57-6a5d-4342-8f04-7ca389ae733f","seri":"satis_d14","sira":7,"tarih":"05.08.2022 20:49:30","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":45,"guid":"e26215b0-37eb-4263-a7d8-04e5996610ca","seri":"satis_d14","sira":6,"tarih":"05.08.2022 20:49:23","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":44,"guid":"c92f0123-3bf4-441c-913b-e7a435c57e8f","seri":"satis_d14","sira":5,"tarih":"05.08.2022 20:47:14","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":43,"guid":"c0d6699f-5857-4ea2-aef7-602b9b99f615","seri":"satis_d14","sira":4,"tarih":"05.08.2022 20:46:33","detay_kayit_sayisi":0,"detay_stok_miktari":"0"},{"belge_id":42,"guid":"92997f5d-b4bd-4777-a581-624d24edf471","seri":"satis_d14","sira":3,"tarih":"05.08.2022 20:44:26","detay_kayit_sayisi":0,"detay_stok_miktari":"0"}]
     * result_message : {"code":"0000","message":"İşlem Başarılı","type":"S"}
     */

    private ResultMessageBean result_message;
    private List<ResultBean> result;


    public static class ResultMessageBean {
        /**
         * code : 0000
         * message : İşlem Başarılı
         * type : S
         */

        private String code;
        private String message;
        private String type;


        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class ResultBean {
        /**
         * belge_id : 142
         * guid : 4f0ea2e4-3ea4-407a-b5da-a091be005ee0
         * seri : satis_d14
         * sira : 103
         * tarih : 15.08.2022 10:47:23
         * detay_kayit_sayisi : 0
         * detay_stok_miktari : 0
         */

        private int belge_id;
        private String guid;
        private String seri;
        private int sira;
        private String tarih;
        private int detay_kayit_sayisi;
        private String detay_stok_miktari;
        private String cari_kod;

        public int getBelge_id() {
            return belge_id;
        }

        public void setBelge_id(int belge_id) {
            this.belge_id = belge_id;
        }

        public String getGuid() {
            return guid;
        }

        public void setGuid(String guid) {
            this.guid = guid;
        }

        public String getSeri() {
            return seri;
        }

        public void setSeri(String seri) {
            this.seri = seri;
        }

        public int getSira() {
            return sira;
        }

        public void setSira(int sira) {
            this.sira = sira;
        }

        public String getTarih() {
            return tarih;
        }

        public void setTarih(String tarih) {
            this.tarih = tarih;
        }

        public int getDetay_kayit_sayisi() {
            return detay_kayit_sayisi;
        }

        public void setDetay_kayit_sayisi(int detay_kayit_sayisi) {
            this.detay_kayit_sayisi = detay_kayit_sayisi;
        }

        public String getDetay_stok_miktari() {
            return detay_stok_miktari;
        }

        public void setDetay_stok_miktari(String detay_stok_miktari) {
            this.detay_stok_miktari = detay_stok_miktari;
        }

        public String getCari_kod() {
            return cari_kod;
        }

        public void setCari_kod(String cari_kod) {
            this.cari_kod = cari_kod;
        }
    }

    public ResultMessageBean getResult_message() {
        return result_message;
    }

    public void setResult_message(ResultMessageBean result_message) {
        this.result_message = result_message;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }


}
