/*
 * Copyright 2020 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package ai.djl.integration.tests.nn;

import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDArrays;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;
import ai.djl.ndarray.types.DataType;
import ai.djl.ndarray.types.Shape;
import ai.djl.nn.transformer.ScaledDotProductAttentionBlock;
import ai.djl.training.ParameterStore;
import ai.djl.training.initializer.Initializer;
import ai.djl.training.initializer.NormalInitializer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ScaledDotProductAttentionBlockTest {

    private double[] keySequenceInitialization = {
        0.5433554167652563,
        0.6700505869836255,
        0.7917800344598416,
        0.7219388754446154,
        0.7237867830817906,
        -0.9439666666020106,
        -0.8176853651805833,
        0.46666854161985283,
        -0.45328644850496747,
        0.2451263256720806,
        0.6826720337721774,
        0.5252099271801092,
        0.4547030249729449,
        0.05578605394118008,
        0.7636131576087515,
        0.34691544598526347,
        -0.4223406413953872,
        0.010792356938105874,
        -0.7697181550260781,
        0.770630682749762,
        0.9135530754580925,
        -0.536285109915009,
        0.27885180830230505,
        -0.20855085819604047,
        0.2893609263089323,
        0.0839009738773635,
        -0.21892658586251001,
        -0.7312464697534438,
        0.7618045025676674,
        -0.9547597774586867,
        -0.45943861205076386,
        -0.7779794226939785,
        0.10649011194822466,
        -0.8526536335012771,
        -0.801090570735429,
        -0.6124920838302828,
        0.9414728315989556,
        0.019429725157060318,
        -0.3824895225551468,
        0.6474989607127521,
        -0.8484425361084189,
        -0.6870160621541213,
        0.10129383829915395,
        -0.5338104234508145,
        0.9702218904134117,
        0.6514169616209582,
        -0.8778622289704143,
        -0.3944636858980659,
        0.8069935433596735,
        -0.7442940465594978,
        -0.05890496573937165,
        0.195885162551809,
        0.42722175363053005,
        -0.3495802898907745,
        -0.6659419153731256,
        0.7852811896322829,
        -0.5217362217680803,
        -0.6615279151612286,
        0.13925698359795957,
        -0.8893764460209648,
        0.06031367268251864,
        -0.14290171579474942,
        0.7044752655926283,
        -0.4843094127305496,
        0.555387649222137,
        -0.791573220245051,
        -0.9944228401218111,
        0.754169026750835,
        0.734519689648367,
        0.6633971426423722,
        0.22977730555726672,
        0.6181975742228218,
        0.5641232092003994,
        0.6002124384290473,
        -0.7790496523416288,
        -0.35208560512253384,
        0.6911654545326018,
        -0.32831959716317693,
        -0.2545135477719611,
        -0.22427583997829048,
        0.6969989919091986,
        -0.7717648206582868,
        -0.8306473325728776,
        0.76856627499084,
        0.8687708692831784,
        0.7451397479928754,
        0.5484079527077375,
        -0.7985713091674691,
        -0.3103677391900075,
        -0.45882677010430517,
        0.5276145180015426,
        -0.19828638977839197,
        -0.20210778039274047,
        -0.7441134842765367,
        -0.045934094409463944,
        0.4857571391165598,
        0.08048456028569184,
        -0.24047190920574057,
        0.9434247416689348,
        -0.593436990887457,
        -0.6829113505105966,
        0.9218829204030328,
        0.3701853997768185,
        -0.1895793624146167,
        -0.8553750988618865,
        -0.8111427439836223,
        -0.39329394713222365,
        0.4711811648766182,
        -0.5823708994579919,
        0.7709597423853378,
        0.9531591113994193,
        0.15824579517146153,
        0.9992869081132469,
        -0.3913995607769629,
        0.8092568728900558,
        0.10915755307618813,
        0.796928681686969,
        0.766024162388321,
        -0.6222880064679357,
        0.38793137492912155,
        0.9480063429048835,
        -0.6528044620690581,
        0.1665545255814347,
        -0.15370654909485282,
        -0.9453662282745394,
        0.11744105168304064,
        -0.7851541747651363,
        0.591891174777289,
        -0.0698998012322729,
        -0.6831653374467752,
        -0.22027783787174582,
        -0.236219364490043,
        0.3093714363242994,
        -0.30650514438715537,
        -0.056024270321067604,
        -0.3981875649965556,
        -0.36080170299063785,
        -0.7576065658666713,
        -0.6083390341823343,
        0.9177717188424561,
        -0.4399437793216314,
        -0.48349810473442867,
        -0.9139656561165468,
        -0.7479037102142225,
        0.2109222842316203,
        0.003832761069779478,
        0.760209375805273,
        0.5586731549494404,
        -0.4640923696567947,
        0.3682403812914319,
        0.6113351788568269,
        0.2939090391000765,
        -0.6912931747685858,
        0.9653766944927651,
        -0.637351412410152,
        0.533485434473443,
        -0.37193689116201845,
        0.04813032611909862,
        -0.9852485903983026,
        0.5572565521198451,
        -0.7510989091192288,
        0.5265896005940367,
        -0.5404855596499603,
        0.672022359534471,
        0.986768043396681,
        0.6949159677337491,
        -0.9118952322454932,
        -0.29984643056603644,
        -0.6230402394807848,
        -0.9235667498868634,
        -0.6712803211083276,
        0.858909564770346,
        -0.8542559715073224,
        -0.221690994466629,
        -0.7899642194428831,
        -0.7777901169461172,
        0.4917128040087071,
        0.8448639281575321,
        0.7578457100003289,
        0.9166433097510496,
        -0.46959858310569924,
        -0.907238383674942,
        0.5071510725479582,
        0.3951201712215293,
        -0.9357741240868152,
        -0.9938593717727924,
        -0.3552033356367088,
        -0.7491952942423683,
        0.5306339831415401,
        -0.06554693444267157,
        0.12233225755942856,
        -0.4638577009069804
    };

    private double[] querySequenceInitialization = {
        -0.20672949219836934,
        0.7934180173701275,
        -0.9170253275232092,
        0.9729176111889186,
        0.33295654062490665,
        -0.4712032121915066,
        0.8939872125784751,
        0.12905917107620501,
        0.9726603771297706,
        -0.6822805673136545,
        0.4357695030112616,
        0.9497961295760602,
        -0.2817597345912579,
        -0.10537592042852073,
        0.8281760732869059,
        -0.23248265736631302,
        -0.9701097398117169,
        0.2777446783171318,
        -0.35337689596588984,
        -0.24668120073230404,
        -0.8583142148651541,
        -0.6310017226330884,
        -0.9379726283729231,
        -0.5314958620077257,
        0.14593039894184612,
        -0.4342168511298248,
        -0.5211297346051995,
        -0.3562607487857177,
        -0.5624072506908524,
        0.9361034322214539,
        0.6238703035437716,
        0.32021624238117075,
        0.13170337510681907,
        0.054283928230322465,
        -0.2356012489813264,
        0.7004049758676962,
        -0.5179540427921332,
        0.7208161181565382,
        -0.05252691930565656,
        -0.5688012437890593,
        -0.08346433479062809,
        0.648086592275744,
        0.34789761756252346,
        0.37896942162396274,
        -0.6228612717054369,
        -0.8298150416117791,
        0.17898664525086017,
        0.0882520215485052,
        -0.8743217545918489,
        0.4828772433708344,
        0.4510363809358702,
        -0.9688656278243868,
        -0.3877238150236251,
        -0.7341019858269027,
        -0.1334744362322393,
        -0.7893864909071946,
        0.29211246216282927,
        -0.4648142880586812,
        0.18247754740884137,
        -0.7924248905089433,
        0.24362196138859282,
        -0.01370759125025689,
        -0.597062800022587,
        -0.81400232327584,
        0.9881791216242743,
        -0.5808488267819281,
        -0.35537633444195804,
        0.37781519598516833,
        -0.9943192318474281,
        0.6685714639501805,
        -0.7875700912554042,
        -0.5022404662306517,
        -0.6886782137944218,
        -0.04426713181134745,
        -0.42356864605545663,
        -0.038018410892452836,
        0.5263896514676636,
        -0.0580395625701593,
        0.1820777794235704,
        -0.7657104074805883,
        0.9111709024409842,
        0.8624203528632179,
        0.46123477147131653,
        -0.32585730369578747,
        -0.7719303798097943,
        0.2905585710749825,
        0.08247872776540177,
        0.7605368962048462,
        0.6214465289605304,
        0.8596015690949608,
        -0.5417786546403682,
        0.36040220102577813,
        0.29749643681563587,
        0.2673109747401874,
        -0.738439870428359,
        0.7959445578176108
    };

    private double[] valueSequenceInitialization = {
        0.8387343722652341,
        -0.8959296731218322,
        -0.0023640856446414116,
        -0.6216355297315141,
        -0.5575360503723565,
        -0.40745641132980337,
        0.8087923301168944,
        0.5311948902337691,
        -0.3768014377460611,
        0.8037773207688705,
        0.857859902829198,
        0.5045160877260539,
        0.14492073468240751,
        0.6607240682791551,
        0.0826063713387839,
        0.6671954355482752,
        0.5131102752777252,
        -0.36079604281006894,
        0.9063632659779768,
        0.022573794555454052,
        -0.6788373629875635,
        0.4582165101346589,
        -0.2441352638225791,
        0.8729359611444862,
        0.3620832740612294,
        -0.5416216508624483,
        0.3737601432546205,
        0.14217995891043644,
        0.33902232417678757,
        -0.8359827990955584,
        -0.6067851072988149,
        -0.30904558246357916,
        -0.1584597607526106,
        -0.4330680273708363,
        0.012043298277604864,
        0.4498404569917207,
        -0.5764327847011277,
        0.4982325056845085,
        -0.7340372610871426,
        0.10643468117694188,
        0.9467902368110328,
        -0.007509351112208407,
        -0.17738381859643937,
        -0.15860422326237544,
        0.9198016887909253,
        0.893861611214642,
        0.029493739599421476,
        0.9908282196681526,
        -0.38314953192744894,
        -0.5567368299727429,
        -0.503661117093879,
        -0.6327860121143272,
        0.34437209312947026,
        0.12858643400641445,
        0.6487163952908805,
        -0.6370019500389597,
        0.7374634531796185,
        0.649539717798808,
        0.6504655828496946,
        0.053619358274827444,
        -0.32602110124172334,
        0.8373342974684213,
        0.5885344981927225,
        0.8955691509584638,
        -0.13081794230048804,
        -0.4170404384663102,
        -0.2933273135660248,
        0.2332558552964854,
        0.8359096784259403,
        -0.1271984691525394,
        0.913240660195527,
        -0.03622433123765245,
        -0.7954495334794758,
        0.00018380159995734502,
        -0.9823963958440509,
        0.29047301743776566,
        -0.21069511972905852,
        -0.12403356138792487,
        -0.7178435714175622,
        -0.5610145311262229,
        -0.3422249369500163,
        0.8347457211107143,
        0.7077937004419013,
        -0.8010305381419254,
        0.6256556508841113,
        0.5806773844207729,
        -0.09015567497762533,
        0.22029760891323158,
        -0.9071752912510245,
        -0.3953664063672382,
        -0.4922759300986821,
        0.4521322826763563,
        -0.09992997408551263,
        0.33770580447134524,
        0.5504841688060507,
        -0.03819414249529829,
        0.6177509210593994,
        0.661051721159746,
        -0.9149144542670609,
        -0.03352406308375144,
        -0.8930863769784829,
        0.6282811563315469,
        0.20028162049102516,
        0.4947593158739565,
        0.5840931725631044,
        0.9549147763823564,
        -0.021302617050677375,
        0.3571050363524626,
        0.7261010071415726,
        -0.1021048329745069,
        0.49756032791929194,
        0.7240399364686607,
        -0.2239297582112696,
        -0.604936139433057,
        0.3664250460760079,
        -0.7333007001697998,
        0.7546756765850806,
        0.8820045613413239,
        -0.07989318319901417,
        -0.13150971858555605,
        -0.42839663651862536,
        0.003492812474136686,
        0.841630842260473,
        -0.07160823563084473,
        0.17756707788777293,
        -0.8039372961931228,
        -0.06679429221130317,
        0.19820318215878263,
        -0.8226453314009103,
        -0.03576190648797284,
        0.4846922717560913,
        0.5103501326201141,
        -0.6744435483752826,
        -0.2028271524066647,
        0.4294658724331022,
        -0.10290237967316229,
        0.3095193748898317,
        0.7027978519886611,
        0.4734273834052165,
        0.9973799062185895,
        0.31919689153636366,
        0.6629551789683172,
        0.7306872752414149,
        -0.46783725170703017,
        -0.5154142350682558,
        -0.8346019132978217,
        0.9223807997600955,
        0.9120408261120914,
        0.31122792493937834,
        -0.5586910812804078,
        -0.15477065700880588,
        0.29645797805096663,
        -0.3951693866291026,
        -0.635505950575858,
        -0.36301495684872465,
        -0.14028511728365012,
        -0.0942068028318368,
        0.4453119650025301,
        0.6122540475354583,
        -0.03791286093239732,
        -0.6119352847064947,
        -0.09360749384250888,
        0.05725028089025841,
        0.041515093039762085,
        0.4040295993031311,
        0.3179340145825671,
        0.17947510978302694,
        0.3754801546686273,
        -0.9437369370352944,
        0.3208779439284144,
        0.8948208983624228,
        -0.23532210593204894,
        -0.7170051465147984,
        0.41273684872638206,
        -0.41340716558927415,
        -0.27204115312351274,
        -0.3960506530471417,
        0.1113822250441836,
        0.679428416781849,
        -0.5566105707057574,
        -0.7479085044654814,
        -0.887706907833268,
        0.8286889642971191,
        -0.1521881930539597,
        -0.26484544571165847,
        0.49192040773071,
        -0.17207679921671915,
        0.8122200858639657,
        -0.43129778422558873,
        0.8346850780111112,
        -0.5480110287861089,
        -0.37081471571122915
    };

    private int[] attentionMaskInitialization = {
        0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1,
        1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 0,
        1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 0, 1,
        0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 0, 0,
        1, 0, 1, 0, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1,
        0, 1, 1, 0
    };

    private double[] keyKernelInitialization = {
        -0.552462949532553,
        0.8640959804421433,
        0.8875097494739581,
        0.4080106768375462,
        -0.7741843106976858,
        -0.616301614417401,
        -0.9052725220201778,
        -0.9523796234061612,
        0.5185144475032306,
        0.6345046782730648,
        0.6945526165161726,
        0.5810646143273996,
        -0.27348336476361923,
        0.9917464083489438,
        0.09090892609735168,
        -0.1256445800842405
    };

    private double[] queryKernelInitialization = {
        0.6404355908703683,
        0.15032744332398695,
        0.5294399655273316,
        0.8531406665908883,
        -0.2427100237942328,
        -0.6106209394079312,
        -0.6182979390685879,
        -0.48798796969366776,
        -0.6395169389974542,
        0.2796846922010914,
        -0.6297496577873305,
        -0.06196984437647801,
        -0.17305040446271858,
        0.8223681029319088,
        -0.90906702095422,
        -0.8505768171570054
    };

    private double[] valueKernelInitialization = {
        0.18453530532376616,
        0.015213038435369697,
        -0.6500067600915183,
        -0.8332479953581389,
        0.8652707506210586,
        0.01235247322397437,
        -0.3240403097273974,
        0.473069404521947,
        0.7793841254668432,
        -0.9117980610504623,
        -0.4508306616791893,
        -0.4229775337544519,
        0.04246038486760417,
        0.6356346894074305,
        0.7561416308526459,
        0.8461227367530886
    };

    private double[] resultKernelInitialization = {
        -0.552462949532553,
        0.8640959804421433,
        0.8875097494739581,
        0.4080106768375462,
        -0.7741843106976858,
        -0.616301614417401,
        -0.9052725220201778,
        -0.9523796234061612,
        0.5185144475032306,
        0.6345046782730648,
        0.6945526165161726,
        0.5810646143273996,
        -0.27348336476361923,
        0.9917464083489438,
        0.09090892609735168,
        -0.1256445800842405
    };

    private double[] expectedResultValues = {
        -0.3095735999004229,
        0.00237555424074426,
        0.3337035825269639,
        0.17919823536404347,
        -0.4435986284013015,
        0.9143559113108888,
        0.8062539096154936,
        0.3966688984706969,
        0.30335643038884536,
        -0.4187304015982195,
        0.6569744150230268,
        0.7376332742056639,
        -0.1446956909298942,
        -0.5554338811570781,
        -0.48492395920389264,
        -0.3762543272666837,
        -0.2727746370124599,
        -0.13571822515170215,
        -0.3939529268297852,
        -0.4032290536558959,
        0.06415094437869029,
        -0.9473449051826315,
        -0.3024756148437248,
        -0.08489517881952786,
        -0.0008993272714931527,
        0.3852053680359482,
        0.6087189667284708,
        0.453787277829681,
        0.1841554186174333,
        -0.8826218991869997,
        -0.1806768372551073,
        0.05934234227257597,
        0.3154954630270635,
        -0.7640907412107449,
        -0.3616678587155871,
        -0.07001054082011655,
        -0.14073514053251943,
        0.15414144932032348,
        -0.5576566389780803,
        -0.5496507290790408,
        -0.3393878232703066,
        0.5680243525661384,
        0.7743954464816167,
        0.48299493725875253,
        -0.27652765947941976,
        0.6622996354129991,
        0.9244324259608685,
        0.6204809970566573,
        -0.42533785992049944,
        0.7170214038816095,
        0.45308619593075494,
        0.12636658474820694,
        -0.5235463384082152,
        -0.030656978109948052,
        0.19465428200019672,
        0.005341896705663131,
        -0.057118236371839576,
        -0.6095622948381143,
        0.15805566088648304,
        0.19959951230538575,
        -0.9001936513545599,
        1.5805527800596955,
        0.9031428888907139,
        0.20855196355609762,
        -0.051443055437815924,
        0.6909362778342901,
        0.6986415649402097,
        0.4681800638309145,
        0.19701187728122105,
        0.34263602825556844,
        0.15182663664036836,
        0.1280697271903719,
        0.3108566963401479,
        0.3324104248497489,
        0.12548321038366111,
        0.13502828978824682,
        0.22485702623298892,
        1.0997239983164415,
        0.9018037484739146,
        0.6300142848851567,
        0.01846762993277408,
        0.05437232412771438,
        -0.028094703987882767,
        -0.038832523600456505,
        0.3659606889694307,
        1.4584392693972232,
        0.7501445596813822,
        0.5291008269402591,
        -0.1363734433923879,
        0.7795779823707656,
        0.18846015385807466,
        -0.011225418183752764,
        0.19787652186133636,
        0.9802469142815473,
        0.4486708224236956,
        0.2931098725215753
    };

    @Test
    public void testMaskedAttention() {
        // The following test runs one forward pass of the block using hardcoded initializations.
        // These were tested against the original google tensorflow transformer code first to get
        // the result of the reference implementation.

        int batchSize = 8;
        int fromSeqLength = 6;
        int toSeqLength = 3;
        int embeddingSize = 4;
        int numAttentionHeads = 2;

        Shape fromShape = new Shape(batchSize, fromSeqLength, embeddingSize);
        Shape toShape = new Shape(batchSize, toSeqLength, embeddingSize);

        NDManager manager = NDManager.newBaseManager();
        NDArray keySequence =
                manager.create(keySequenceInitialization)
                        .toType(DataType.FLOAT32, false)
                        .reshape(fromShape);
        NDArray querySequence =
                manager.create(querySequenceInitialization)
                        .toType(DataType.FLOAT32, false)
                        .reshape(toShape);
        NDArray valueSequence =
                manager.create(valueSequenceInitialization)
                        .toType(DataType.FLOAT32, false)
                        .reshape(fromShape);
        NDArray attentionMask =
                manager.create(attentionMaskInitialization)
                        .toType(DataType.INT8, false)
                        .reshape(batchSize, toSeqLength, fromSeqLength);
        Initializer keyKernelInitializer = new TestConstantInitializer(keyKernelInitialization);
        Initializer queryKernelInitializer = new TestConstantInitializer(queryKernelInitialization);
        Initializer valueKernelInitializer = new TestConstantInitializer(valueKernelInitialization);
        Initializer resultKernelInitializer =
                new TestConstantInitializer(resultKernelInitialization);
        NDArray expectedResult =
                manager.create(expectedResultValues)
                        .toType(DataType.FLOAT32, false)
                        .reshape(toShape);

        ScaledDotProductAttentionBlock block =
                ScaledDotProductAttentionBlock.builder()
                        .setEmbeddingSize(embeddingSize)
                        .setHeadCount(numAttentionHeads)
                        .optAttentionProbsDropoutProb(0.0f)
                        .build();

        block.setInitializer(new NormalInitializer());
        block.getKeyProjection().setInitializer(keyKernelInitializer, "weight");
        block.getValueProjection().setInitializer(valueKernelInitializer, "weight");
        block.getQueryProjection().setInitializer(queryKernelInitializer, "weight");
        block.getResultProjection().setInitializer(resultKernelInitializer, "weight");
        block.initialize(manager, DataType.FLOAT32, fromShape, toShape, fromShape);
        ParameterStore ps = new ParameterStore(manager, false);
        NDArray result =
                block.forward(
                                ps,
                                new NDList(
                                        keySequence, querySequence, valueSequence, attentionMask),
                                null)
                        .head();
        boolean allClose = NDArrays.allClose(result, expectedResult, 1e-04, 1e-07, true);
        Assert.assertTrue(allClose);
    }

    public static class TestConstantInitializer implements Initializer {

        private double[] constants;

        public TestConstantInitializer(double[] constants) {
            this.constants = constants;
        }

        @Override
        public NDArray initialize(NDManager manager, Shape shape, DataType dataType) {
            // test data is from TF, to get the same result the weights need to be transposed
            return manager.create(constants).toType(dataType, false).reshape(shape).transpose();
        }
    }
}
