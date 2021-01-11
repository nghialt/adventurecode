package main.kotlin

const val SAMPLE_INPUT = """
mxmxvkd kfcds sqjhc nhms (contains fish, dairy)
trh fvjkl sbzzf mxmxvkd (contains dairy)
sqjhc fvjkl (contains soy)
sqjhc mxmxvkd sbzzf (contains fish)
"""

const val INPUT_1 = """
dkgkc qxsgkzt zsqn mhlm lzxpk rmd bhxx gbzczpn jbxvkvf gqsj qpdb bdjxjvj rkcngr ztdctgq lgllb jlmlz qcrf vmz fllmxc krg rltc djsvj gxml prgh kcfjnr pjzqbc rqccts sgqj zclh xsfpzncj qqttxln mpqtc hzkdqz hxbl fgjxhf xlknfh mdbq dggmkht cdvjp dlgsd jbdqm xgtj lpts cz qrmvtk nhnsh jfddkg bdnrnx (contains nuts, shellfish, dairy)
rjrn jzjhcm rtcdm pzcgr gvfsv bdnrnx djsvj ztdctgq dnrgnnr ffqndb gpmvkrt dzhf sndrz vmnfpd bzxvd xlknfh xtzqb jdggtft ckbsg fjndlnv qsqcjd vhsp jqhzc mdpql qdqfl dggmkht vgfknzx sjpcmz spqqb qpdb dkgkc lsnj bchsc rkcngr jfddkg ppb pzh lflv tzqxpq rglpnc zclh hcsmg qxsgkzt cdvjp dqxvmx vlsprc qhgg ztpvmk lgllb dfzbpz sbbkvr xsfpzncj sjpx lvmm jbxvkvf bxv gbzczpn zzlfrts rqccts kjsll xgtj lzxz pllnld mxkfs mdbq nprb mbnr drpn dfvkm hxbl dzqvbsq kfft ccqf lcbbcb jtmlcg zsqn rltc cz dlgsd qddlv (contains fish, eggs, soy)
fllmxc xsfpzncj jsqng tzzfktn jdggtft jfrkn cmzlkx dnrgnnr vtbdr qqttxln drpn jtmlcg rjrn rqccts rmd hzrzc qpdb pzcgr ckbsg kspzjr lflv skhxhp hzkdqz gvfsv jpztz nbkdh hxbl ggmgdv bchsc xtzqb qrmvtk jbxvkvf gzzb spqqb sjxlr fsmhfr fjndlnv lzxz mdbq sjpx jlmlz gbzczpn qsqcjd lzxpk bdnrnx vlsprc qtsq vmz xgtj vbxnml krg cmrlm lmrbfm rdkvvnb jqhzc ktgf lxhpmc rkcngr sjpcmz tzqxpq vpqxh ztdctgq chbpkvt pzvbfr gqpdph kht jhcr cdvjp tpgm mvhlr xvbdz vgfknzx bdjxjvj kjsqxp jrkbs bzxvd fdndh dzqvbsq ztpvmk zclh hbxq rglpnc fgjxhf vsns dfvkm pjzqbc mnptn dkgkc qhkq cqlpzd kjsll (contains shellfish, nuts, dairy)
fllmxc nhnsh rglpnc gqpdph lvmm dlgsd jsqng pjzqbc hbxq xsfpzncj fbllk qhkq hfmkj rmd xvmsx cglt jzppz qcrf cdvjp kkcz ckbsg cmzlkx mdms vrhrt xjgv zclh lsnj qddlv lmrbfm jqhzc jrkbs gzzb qxsgkzt jdggtft bdnrnx vpqxh rltc rjrn ztdctgq lgllb ppb rkcngr dzhf xqdl sbbkvr pkknn sjpx skhxhp kcfjnr jlhpkg xvbdz bhxx cz nbkdh djsvj vhsp dkgkc ghshq pzcgr xgtj kmkcksz jbxvkvf jpztz dzqvbsq cmrlm gmzjtc vgfknzx gqsj drrvln (contains eggs)
gbzczpn tzhq kkcz ztdctgq rkcngr cglt jfrkn jtmlcg vpqxh xvbdz fdndh pzvbfr dfvkm vbxnml rglpnc gbv hxbl dfzbpz tpgm chbpkvt qhgg bdnrnx sjpcmz dnrgnnr qsqcjd sbbkvr jndts qxsgkzt mhlm zclh dkplsn zbqhtv fsmhfr kht vgsxs djsvj mxkfs bhxx mdbq jknscx jdggtft pzh mvhlr drrvln kcfjnr cmzlkx rdkvvnb vlsprc mxnjztb sjpx lgllb lzxz bdjxjvj dkgkc tjsvdhn jzppz fllmxc jrkbs xgtj hzrzc gqsj rqccts kspzjr sndrz cdvjp sgqj (contains peanuts, dairy)
vtbdr qhgg bzxvd zbqhtv gsdm jknscx cglt fnr xtzqb rjrn lvmm ztdctgq lzxpk sjpx qglnp qpdb fdndh dggmkht jbxvkvf vsns nvdlxkc ggmcmz gzzb qcrf pllnld jbdqm pzvbfr lxhpmc jzjhcm sjfht jtmlcg sjxlr pbfzgfx xlknfh zsqn vbdnhp prgh dzqvbsq chbpkvt cdvjp qdqfl rmd rtcdm mdpql bdjxjvj gbzczpn kcfjnr vrhrt xgtj tzqxpq jrkbs dfzbpz jzppz mdbq bdnrnx lcbbcb pkknn lflv rglpnc dnrgnnr vmz jdggtft rkcngr sndrz ppb ffjb rqccts jqhzc mbnr lgdv vbxnml sdgjxm xmpqj cz (contains dairy, fish)
jbxvkvf spqqb bzxvd mxkfs lvmm kjsll jlmlz ztdctgq dkgkc pzh xmpqj tzzfktn prgh jtmlcg lzxpk sjpcmz mkv vpqxh jqhzc rtcdm djsvj sjpx lgllb ghshq mdms lcbbcb jfrkn rjrn ggmcmz zzlfrts xtzqb mbnr jdggtft kspzjr tjxrc kmkcksz hcsmg jlhpkg cdvjp bdnrnx kjsqxp dlgsd tzhq xvmsx jzjhcm pjzqbc qdqfl skhxhp ktgf nsndth jdrlzk ngmk gqpdph dfvkm mhlm rmd jbdqm mdbq gqsj mvhlr (contains peanuts, soy, fish)
qrmvtk hfmkj kkcz rmd qxsgkzt mdbq rhjhmvj cz pkknn jdrlzk nsndth ggmgdv tzhq xqdl gpmvkrt mpcsjs xvmsx xgtj mbnr mhlm xxzjz qdqfl zclh rjrn tjsvdhn vbxnml jjnd bmqvll ggmcmz kcfjnr jdggtft bchsc bdnrnx drrvln zsqn ckbsg tzzfktn pzh lgdv mkv gmzjtc jlmlz pzvbfr ztdctgq jfddkg fxbxj xlknfh lcbbcb nvdlxkc lpts fgjxhf lxhpmc cqlpzd tjxrc rdkvvnb xqsn dzhf qtsq qglnp bzxvd sjpcmz vpqxh zlqcps ppb lgllb vlsprc mvhlr jhcr (contains soy, fish)
gpmvkrt vmz mnptn pbfzgfx bxv vbdnhp fllmxc tgtzx dkgkc cdvjp xmpqj jhcr mdms cqlpzd jpztz xlknfh kjsll xgtj bchsc bhxx rmd pjzqbc xsfpzncj mdbq mpcsjs kht dfvkm cmzlkx vrhrt jzppz sjpx rkcngr mhlm kspzjr prgh zbqhtv lgllb jdggtft pzh qglnp jlhpkg vpqxh bdnrnx mbnr (contains fish)
gqsj vbdnhp xgtj ztdctgq gvfsv xxzjz jsqng gmzjtc hbxq spqqb fdndh mxkfs kjsll mvhlr lcbbcb sgqj pzh dggmkht bdnrnx jlhpkg zjj lgdv jhcr qtsq sjpcmz nbkdh mdpql rdkvvnb gsdm dzhf lgllb vbxnml jdggtft mpqtc ckbsg cdvjp mdbq kht hcsmg sjxlr chbpkvt pfmnx cmzlkx ppb mpcsjs jzjhcm qglnp gbv hzkdqz rtcdm zlqcps pjzqbc vmz dkgkc fxbxj xqdl kspzjr qhkq rqccts qpdb gqpdph zbqhtv djsvj xlknfh ggmcmz qrmvtk dkplsn vgfknzx bmqvll (contains nuts, eggs, fish)
dzqvbsq vhsp kmkcksz vsns pzh mpqtc sndrz gzzb xtzqb vpqxh mdms bdnrnx tzhq ktgf djsvj lgdv tzzfktn qhkq qtsq jbdqm jndts rtcdm xxzjz vbdnhp lmrbfm mxnjztb sjfht bchsc sjxlr vmz drpn spqqb mdbq gbzczpn dnrgnnr gbv pllnld cdvjp qhgg ggmcmz cmrlm qddlv lvmm kfft tzqxpq gsdm vlsprc jdggtft xgtj zsqn kjsll pzcgr kjsqxp fxbxj dkplsn ffjb gmzjtc xqdl mdpql dqxvmx fnr jhcr cglt gqsj gbcsmlr tjxrc dkgkc lflv bxv xqsn hxbl jjnd sjpcmz qcrf jzjhcm pjzqbc jlhpkg vtbdr qdqfl chbpkvt mpcsjs lxhpmc gqpdph rmd kspzjr ztdctgq prgh jsqng vgfknzx fgfl bdjxjvj nxc lcbbcb (contains fish)
fgfl kcfjnr fdndh fjndlnv gbv ktgf kht skhxhp chbpkvt qpdb mdbq vrhrt nvdlxkc mxnjztb jdggtft lgdv rltc bdnrnx vpqxh hzkdqz ztdctgq sdgjxm jjnd dlgsd spqqb sjpx rmd nprb vmz bhxx cqlpzd pjzqbc mkv qxsgkzt xgtj fllmxc zjj jzppz tjxrc gxml fbllk rqccts hxbl qhgg lgllb mdms gpmvkrt ggmgdv tgtzx ghshq nbkdh pgdb bxv drrvln rglpnc jknscx dzqvbsq (contains peanuts, eggs, fish)
lgdv nbkdh dggmkht qcrf kspzjr qpdb lpts drrvln jhcr fllmxc xjgv rdkvvnb xxzjz jbxvkvf pgdb mxnjztb xmpqj pjzqbc jfrkn rtcdm dkplsn bdnrnx vbdnhp rqccts vgsxs hcsmg dfvkm xtzqb mpqtc kjsll ktgf cqlpzd kmkcksz jdggtft lflv lcbbcb bchsc rhjhmvj ffqndb xvmsx tzzfktn gbzczpn jfddkg kfft cmrlm rmd nhnsh hxbl vmnfpd dqxvmx jknscx pkknn vmz dnrgnnr lgllb mdbq gvfsv zbqhtv xlknfh fsmhfr jndts mkv fdndh qqttxln xgtj fgfl nprb mdms sjxlr fxbxj sbbkvr lzxz rglpnc cdvjp lsnj kkcz hbxq gmzjtc sgqj qrmvtk (contains fish, peanuts)
cdvjp kjsll cmzlkx bdnrnx jdggtft gbcsmlr kjsqxp rglpnc jtmlcg dfzbpz bhxx xvmsx tzhq nsndth tgtzx xvbdz lflv djsvj pzcgr ztdctgq pfmnx jdrlzk zlqcps xgtj pjzqbc dnrgnnr qddlv vrhrt ckbsg lmrbfm lzxz dkplsn dlgsd fbllk rmd ggmgdv tzzfktn pzh dkgkc qtsq vgfknzx mdbq jsqng gxml lsnj (contains sesame, peanuts, shellfish)
cdvjp xgtj mvhlr tzzfktn qddlv vgsxs bhxx tgtzx vgfknzx xqdl gsdm xvbdz mxnjztb mdbq fdndh vtbdr chbpkvt tzhq kkcz vrhrt mnptn pfmnx mkv xxzjz pzcgr ztdctgq bdnrnx pgdb nhnsh fnr hbxq lgllb prgh cglt rmd ppb jdrlzk gbzczpn vsns gzzb jlhpkg lpts vbxnml gjvhr ztpvmk ffqndb kmkcksz krg lzxz pbfzgfx jzppz mxkfs djsvj ghshq dkgkc rglpnc jlmlz (contains fish)
cqlpzd ztdctgq pzvbfr dkplsn zclh djsvj lzxpk kjsll tjxrc zlqcps prgh kjsqxp gzzb vmnfpd nhnsh sjxlr bdjxjvj sgqj kht xgtj mpqtc vbxnml jbdqm gmzjtc jlmlz chbpkvt dzqvbsq pzh spqqb pbfzgfx rjrn zbqhtv jknscx lgllb sjpx drrvln qglnp dlgsd sjpcmz rdkvvnb cglt bdnrnx vbdnhp nsndth dggmkht hbxq mxnjztb cdvjp xvbdz jbxvkvf zzlfrts jzjhcm fgfl hzrzc gqpdph nprb skhxhp hfmkj dzhf vsns bzxvd pjzqbc dqxvmx jtmlcg pllnld ckbsg jdggtft ffjb fnr lxhpmc vtbdr mpcsjs ktgf mdbq (contains sesame)
tzqxpq sjfht ffqndb jpztz jlmlz lgdv ppb sndrz qtsq rkcngr jtmlcg nbkdh dnrgnnr ztdctgq hbxq gzzb vbdnhp cdvjp cz nhnsh fjndlnv ngmk tzzfktn sjxlr zsqn xgtj hcsmg cqlpzd ffjb sjpcmz bdjxjvj xvmsx drpn mpqtc sgqj gxml xqdl mdbq pzvbfr bdnrnx sjpx jrkbs gsdm vrhrt chbpkvt kcfjnr sdgjxm tjsvdhn xsfpzncj djsvj rmd fgjxhf vpqxh ckbsg tjxrc jjnd kfft vlsprc pzcgr pfmnx jknscx vhsp jdggtft jfrkn hfmkj kjsll drrvln gbzczpn xmpqj lcbbcb lzxz mkv kjsqxp tgtzx jdrlzk (contains eggs, dairy, sesame)
spqqb qtsq cmrlm nhnsh mkv rtcdm cmzlkx bxv zjj lmrbfm mhlm fjndlnv gqsj kfft xgtj zzlfrts xlknfh lzxz cglt hzkdqz nvdlxkc lvmm gxml qqttxln dkplsn vgfknzx fxbxj hcsmg jbxvkvf jdggtft dqxvmx ghshq zlqcps jndts bchsc cqlpzd djsvj cdvjp mdbq pzvbfr jlhpkg qpdb rjrn dzqvbsq ztpvmk tpgm lgllb rmd kcfjnr xxzjz jlmlz bdnrnx (contains peanuts, dairy)
ckbsg jdggtft sjpcmz nhnsh dnrgnnr bmqvll ztdctgq qsqcjd rqccts cdvjp fdndh sndrz lflv mdbq dzqvbsq jlmlz qcrf fnr vlsprc qpdb cz jbxvkvf jfddkg bdnrnx jsqng vrhrt dzhf skhxhp nvdlxkc mhlm ccqf jknscx lgllb kkcz xmpqj spqqb tjsvdhn jqhzc ggmgdv mxkfs rmd tgtzx kht jlhpkg vbxnml lvmm vtbdr (contains soy, eggs, fish)
bdnrnx lxhpmc qhgg vbxnml ggmgdv zbqhtv qpdb kht vlsprc jdrlzk dnrgnnr zjj mnptn zclh xtzqb ccqf bdjxjvj pgdb jdggtft fnr xsfpzncj fgjxhf qglnp kspzjr rltc hfmkj pkknn mdbq mvhlr ffqndb ckbsg hzrzc kfft lgdv gpmvkrt ztdctgq vsns dfzbpz pjzqbc rmd mdms hxbl gxml mdpql xmpqj gqsj ghshq xlknfh dzhf lcbbcb bxv sbbkvr dzqvbsq dkplsn qqttxln mhlm rkcngr lzxpk chbpkvt vrhrt vgfknzx ztpvmk sgqj cdvjp vmz bchsc pzcgr zzlfrts sjpcmz vgsxs lgllb jzjhcm kmkcksz gvfsv (contains sesame, eggs, shellfish)
jdggtft sbbkvr ppb lsnj mxnjztb kfft kht kmkcksz cqlpzd ztdctgq mpqtc bdnrnx jbdqm vpqxh dggmkht vgfknzx qdqfl bzxvd bxv rmd kcfjnr nxc pjzqbc lmrbfm zsqn rkcngr tzqxpq bmqvll pbfzgfx vtbdr ghshq pzcgr prgh zzlfrts lgllb cdvjp mdbq hzrzc (contains soy, sesame, fish)
zbqhtv mpqtc fbllk jdggtft xsfpzncj rhjhmvj hzrzc xqdl zclh sdgjxm bhxx jsqng vgsxs dlgsd ppb xmpqj tjxrc fsmhfr mdbq ccqf jzjhcm tjsvdhn jbdqm cdvjp lzxz lgllb qsqcjd jrkbs bdnrnx sjfht mdpql vmz pzcgr jlhpkg jdrlzk mdms drpn nvdlxkc jbxvkvf sjpx sndrz jtmlcg xgtj nxc kspzjr mhlm mpcsjs dnrgnnr mvhlr ztpvmk mxkfs rglpnc gqpdph ffjb rdkvvnb qpdb mxnjztb gsdm xlknfh sjpcmz rmd jjnd qhgg djsvj dggmkht cmzlkx tzzfktn (contains shellfish)
xgtj hfmkj ppb hcsmg rglpnc vbxnml mdbq jqhzc jrkbs rdkvvnb prgh bxv gbzczpn qglnp vsns jdggtft vlsprc gxml nbkdh mkv gvfsv zbqhtv zzlfrts pbfzgfx vmz lgllb gzzb xqsn cmzlkx fsmhfr cmrlm cglt kspzjr fdndh chbpkvt rkcngr qpdb hzkdqz kjsqxp bdjxjvj ztdctgq kmkcksz rltc kht qhgg xjgv bdnrnx zlqcps rjrn kcfjnr bhxx nhnsh mpcsjs tjxrc lzxz rmd (contains peanuts, dairy, sesame)
ckbsg jjnd mdbq xvbdz kjsqxp drpn vmnfpd lzxpk ffjb drrvln dlgsd zclh pjzqbc mvhlr vsns dkplsn mpqtc jhcr gzzb mkv rdkvvnb zzlfrts jsqng lgllb tzzfktn zbqhtv djsvj xqsn hxbl gqpdph qglnp hfmkj rmd xjgv sjxlr qhgg jrkbs kkcz fdndh kspzjr bhxx tjxrc vpqxh xxzjz jdggtft jlhpkg lcbbcb pllnld qtsq rhjhmvj rkcngr nbkdh tpgm nvdlxkc jzppz zsqn cdvjp lgdv dzhf xqdl gqsj fsmhfr jzjhcm gxml pfmnx lxhpmc vhsp jlmlz gbcsmlr ztdctgq bdnrnx (contains sesame)
xsfpzncj hfmkj rhjhmvj lmrbfm sbbkvr ggmgdv jdggtft xgtj mpqtc rkcngr rtcdm ztpvmk cdvjp zbqhtv zjj lvmm cmzlkx gxml mdbq sjpcmz fsmhfr jzppz bmqvll ktgf vbxnml gbzczpn bdnrnx cmrlm kspzjr gsdm qxsgkzt ztdctgq vtbdr jdrlzk gpmvkrt rmd tzzfktn gqsj jtmlcg dfzbpz skhxhp dkplsn (contains shellfish, nuts, dairy)
cdvjp sndrz cqlpzd pjzqbc xqsn bdnrnx mxkfs sjpx pfmnx jknscx mdbq kjsll rkcngr jndts qrmvtk lgdv nhnsh xsfpzncj hxbl xgtj qpdb qddlv lcbbcb vmz tjsvdhn prgh fjndlnv fdndh vpqxh dfzbpz zlqcps rmd nbkdh zclh dfvkm hbxq gsdm lzxz jlmlz vhsp zzlfrts mxnjztb rltc ghshq qhgg gpmvkrt qhkq tjxrc mpqtc rtcdm dkgkc xtzqb pzcgr bdjxjvj xvbdz sgqj xvmsx dzhf ffqndb vbdnhp vsns gbcsmlr jdggtft fbllk lgllb dggmkht gmzjtc vgsxs lzxpk nsndth ztpvmk nvdlxkc bhxx drrvln lflv tpgm hzkdqz vtbdr (contains fish, sesame, shellfish)
djsvj xtzqb tzqxpq jzjhcm kmkcksz bmqvll zjj jknscx lgllb sbbkvr pzvbfr krg kfft xvmsx xgtj tgtzx xqsn sjpcmz xqdl zclh bzxvd rtcdm nsndth vmz rmd lgdv qcrf ggmgdv pfmnx jdggtft fnr tjsvdhn kcfjnr rglpnc pjzqbc nxc jfrkn nvdlxkc xvbdz zbqhtv zsqn lzxz dfvkm spqqb fllmxc ztdctgq bdnrnx lvmm pbfzgfx lzxpk gvfsv fxbxj vgsxs mdms cglt dfzbpz qrmvtk mdbq (contains eggs, soy)
rmd vtbdr dnrgnnr mpqtc qhkq hbxq jtmlcg tzhq rkcngr gsdm qqttxln hfmkj jhcr lzxz lgllb pzcgr dqxvmx lxhpmc mvhlr vlsprc mdpql gpmvkrt gqpdph gmzjtc fxbxj cmrlm jzjhcm xgtj zclh vbdnhp vmz bmqvll kcfjnr ztdctgq fnr bdjxjvj sjpcmz cz dzqvbsq drrvln gzzb gxml prgh bdnrnx qtsq jbxvkvf bchsc ghshq pbfzgfx drpn rdkvvnb sjfht sndrz krg pzh dfvkm jqhzc mdbq zzlfrts sbbkvr pllnld ggmcmz dzhf tpgm tzqxpq vmnfpd lpts kspzjr vbxnml ngmk tjxrc hzkdqz xsfpzncj jsqng qhgg qdqfl bxv pfmnx qglnp nsndth ppb ztpvmk spqqb dggmkht cdvjp lsnj zjj dkgkc mnptn vsns dkplsn (contains sesame, dairy)
jfrkn gpmvkrt lgllb pfmnx ktgf kmkcksz zclh lzxz sjxlr xvmsx lxhpmc gmzjtc qhgg lflv dggmkht rkcngr tjxrc xtzqb gbzczpn mdbq krg ffjb xgtj bxv hcsmg pzh vbdnhp ngmk vsns gjvhr jdrlzk mxnjztb ccqf drpn ztdctgq kfft kjsll jdggtft drrvln nprb qglnp ppb jbxvkvf pzvbfr kspzjr spqqb bdnrnx jhcr ghshq qrmvtk mxkfs gzzb ffqndb skhxhp sbbkvr chbpkvt mbnr rdkvvnb rmd fnr vtbdr tjsvdhn ggmgdv lpts prgh zjj xqdl (contains dairy, peanuts)
bzxvd xgtj kmkcksz fjndlnv qhkq qtsq dggmkht jpztz vpqxh kfft djsvj jjnd cdvjp rqccts qsqcjd qddlv mdbq lmrbfm zbqhtv xlknfh vmz qglnp xjgv rmd vgsxs ccqf gbcsmlr ckbsg jqhzc nsndth gjvhr gsdm mdms ngmk fdndh tgtzx sndrz krg jtmlcg ffjb xtzqb dzqvbsq pbfzgfx skhxhp mkv xmpqj tjxrc hxbl lgllb gvfsv kcfjnr mpqtc pkknn sdgjxm zlqcps dfzbpz vhsp sjpx pllnld tpgm jdggtft jdrlzk tzhq fxbxj qpdb ztdctgq bdjxjvj tjsvdhn pjzqbc xqdl (contains sesame, soy)
jpztz ggmgdv mhlm fbllk pzcgr vpqxh dkgkc fjndlnv qrmvtk kkcz xgtj qqttxln jtmlcg nsndth sjxlr lmrbfm gsdm bdnrnx lgllb ffqndb cdvjp jsqng bchsc lxhpmc dfzbpz jbdqm sdgjxm rjrn nvdlxkc bdjxjvj ckbsg mdbq nhnsh bxv xmpqj ztdctgq qtsq qpdb rmd pjzqbc (contains sesame, soy)
bdnrnx sbbkvr kmkcksz cmzlkx fbllk ffqndb xgtj mpqtc hxbl ztdctgq jndts kjsqxp vbdnhp jtmlcg tgtzx pfmnx qcrf vpqxh cqlpzd hzrzc mbnr mxnjztb bhxx jfddkg bmqvll xsfpzncj zsqn qhgg fnr jlmlz lflv rdkvvnb zclh lgllb jbxvkvf rhjhmvj kjsll xqsn tzhq hcsmg tzqxpq zjj sjxlr mdbq xtzqb kkcz rmd gjvhr vgsxs cdvjp xvbdz prgh xqdl qhkq vbxnml (contains soy, shellfish)
qpdb chbpkvt hcsmg ztpvmk tjxrc hfmkj ffjb qdqfl prgh vmz mnptn nvdlxkc kkcz gbcsmlr xmpqj xqsn drpn gmzjtc gbv sjpx zlqcps xsfpzncj lcbbcb spqqb jrkbs lgllb xgtj vpqxh jzppz jpztz sjxlr dfvkm rmd ghshq kspzjr rltc vmnfpd ztdctgq lzxpk jbdqm ffqndb fgfl nxc rkcngr gvfsv bdnrnx pgdb mdbq vlsprc sgqj cdvjp lflv drrvln zbqhtv jdrlzk qddlv qqttxln (contains fish, eggs, sesame)
"""

const val INPUT = """
qtncs hjlcgk rrvp rgc tzvlm vxkmcmj dxk gpgf fnbqnt fnqxjl cppzn ctnkv czvhncg krjn mft dknlb gnsxbz gljdv htskk kkzqp mpm jckpd xbmj xvph bvh cjbvz tdtzd tdbcfb qrvft mxkh rhcj jcbcxtj hkxqsc dfbnq sxksnzf ckqnfq hbmv xnzqrm ndssl hkzrs rgdz nhhsgd hchdc zgng nsvfk lbgntt gjck grsx ndgfp nptr mmpgm mqxhh csmfzp sp gptcng gdtq bpbdlmg rtnjfqm ssfvs cpvn bgsdh dfxmxd jjgvd hrbdk (contains dairy)
xppdk rgc kntss qkdgt jpvfc sjvrgx tkqng smzhj jdmhd zhj xbmj bvh zgng jvshst rhcj rjt cvcr qgqnjx xbdsf ckqnfq cpvn ldkmxjd hskshtl mtxxb zfrlbz qtncs krjn gkcqxs jttxhczk sxlj fnbqnt ctnkv hdrkr bpbdlmg jcbcxtj mvv tzvlm cnhch vbb qmgfs ndgfp dxk hfkvr dgcq xnzqrm vmhf sp nptr nmfv hkzrs rgphsms dfbnq vcx bnsfss kfpx cbmnb tdbcfb dknlb dtv gdtq tprcc nhhsgd hkxqsc rhrrrbr (contains fish, wheat, shellfish)
sp bvh cnjmk zjzrbq jjgvd mvv krjn dpfvx grsx sgnq lcc qmgfs jcbcxtj cvcr smzhj rhcj kfpx jdmhd qrvft qbbdfr kkzqp ckqnfq drsbbfrp cztkz stnlkxj vfcs xbdsf mxkh mmbqr mckrjz vxkmcmj cppzn ctnkv rgdz hdrkr pvtbbc gjck mft tdbcfb mjndggkb jcmbbfm gnsxbz vrjj hdgfgj hkzrs bpbdlmg rsrxtc rgc bnhmc zfrlbz pnkpb cjbvz mbjt dfxmxd (contains sesame, nuts)
lcc sp cxjkfz bklcz nzcgntfs qvzxxp kfpx tdbcfb grsx vbb pxz bhp mvv gkcqxs tk qmvp mft xdkhj rjt vmhf smpsr tdtzd kfsl fczmkt hskshtl gnsxbz bbxmx cztkz qkdgt zhj bnhmc hkzrs bvh rgphsms dbdmmt bbmpr hrbdk cnjmk lpznnc xrtx gpgf qbbdfr zxr jjgvd bpbdlmg nptr mmbqr hchdc lnhqgk tzvlm pnkpb dgcq hsrjm htskk cbmnb rgc ldg xbmj krjn bn cnhch vdhtgm (contains nuts, fish, wheat)
ngrnd qtc hdrkr qgqnjx sjzsc fhp cbmnb qbbdfr jckpd lpznnc xrrcj ldkmxjd krjn kflk nhhsgd cvpt dfxmxd sjvrgx xrtx bpbdlmg mxkh zbrfr hfkvr xdkhj qtncs pxz bnsfss tk cppzn kbtxv tzvlm tjpsn rgc tkqng nptr rhrrrbr bnhmc bvh skkgc jvshst zjzrbq fjk gljdv hsrjm sp xbmj dtdbzq ndgfp lgsk vxkmcmj nmfv lcc bgsdh ctnkv tdbcfb qrvft bn hzvgn lbgntt ndssl rgphsms qjgrv kntss mmbqr scvzn ktrfs zxr xvph lxntzp hdgfgj gptcng tdtzd jttxhczk xbdsf gdtq md (contains soy, nuts)
qbbdfr mqxhh cztkz kfsl stnjg gkcqxs mxkh kzcpb mbjt csmfzp ndgfp fczmkt hvgh pxz gjck qgqnjx tzqrlb bvh vzzgts mvfqbnlb kntss bzfv drsbbfrp scvzn cjbvz xrrcj kfpx jchrn gxxg stnlkxj hsrjm vbb mckrjz zjgxg vcx md vfhlb hbmv dpcmr qmvp lkkt ldtr jpvfc cvpt tzvlm vxkmcmj czvhncg sxksnzf xvph jckpd krjn ngrnd mpm hdgfgj pnkpb xrtx zxr zbrfr xtnk grsx zfrlbz qqzjfk ldg nptr rgc rhcj hfkvr tdbcfb dpfvx hjlcgk gljdv vrjj qss xbdsf kthm bn bpbdlmg fhp xppdk qtncs (contains shellfish)
qbbdfr jckpd dtv jjgvd rsrxtc dgcq ssfvs hsrjm tdbcfb kntss nhhsgd xdjt vcx nptr zjgxg jpvfc ndssl xrrcj hdgfgj rxpddd tzvlm kfpx bpbdlmg ndgfp zxr bzfv lkkt pvtbbc gkcqxs kkzqp hkzrs qvzxxp bn xbdsf lgsk nzcgntfs sgnq ktrfs sxksnzf qss sp qmgfs rgdz sgn tjpsn mxkh mpm sxlj rgc dpfvx xppdk dfbnq jcmbbfm md mmpgm gptcng lpznnc cppzn vnqh zjzrbq mqxhh vdhtgm cxjkfz jvshst mtxxb rrvp fnqxjl gpgf mgngg bvh (contains peanuts, nuts)
jdmhd lxntzp qgqnjx jvshst jchrn hchdc qbbdfr rgc nhhsgd kfsl kbtxv jttxhczk zjgxg xtnk rsrxtc scvzn zbrfr ssfvs dpcmr kqctvs rhrrrbr mtxxb vmhf tdbcfb csmfzp bpbdlmg mmpgm mxkh zgng cnhch hzvgn rhcj xdjt krjn ldg sgn tghpf vbb rxpddd gdtq lbgntt qtc jjgvd ldkmxjd vzzgts stnlkxj nmfv jpvfc xvph rrvp bvh vfgg bgsdh nsvfk xdkhj mjjgj grsx qvzxxp cppzn vrjj rtnjfqm rjt hdgfgj jcbcxtj vxkmcmj sp (contains soy, dairy)
qmvp zjgxg md sxlj qgqnjx mmbqr jchrn hchdc ldkmxjd qkdgt xppdk rtnjfqm rgdz rsrxtc tk gdtq tghpf bnsfss jckpd htskk krjn bpbdlmg sp fjk bhp lpznnc gkcqxs jdmhd hskshtl hsrjm lbgntt mxkh hbmv hrbdk lkkt bvh qrcmm xnzqrm rgc bnhmc grsx hjlcgk smzhj qss pnkpb rhcj hdrkr tzqrlb kntss (contains nuts, sesame, fish)
vfcs dtdbzq tdbcfb tzqrlb sjvrgx hbmv ctnkv krjn bbxmx vrjj jpvfc csmfzp zxr cbmnb nzcgntfs zbrfr ckqnfq sxlj dgcq qmgfs xdjt tk mxkh dqgl jckpd ssfvs vdhtgm dknlb rgphsms drsbbfrp bvh xtnk vfhlb dfbnq nptr mqxhh bpbdlmg sp czvhncg bnhmc kqctvs dpcmr xnzqrm tghpf sgn cvpt ndssl hkxqsc fhp ngrnd mjjgj gkcqxs qmvp lcc hzvgn vbb (contains dairy, soy)
zjgxg tkqng dfbnq bzfv gkcqxs zjzrbq rxpddd mbjt sgnq hrbdk bvh md jdclr nzcgntfs cpvn bgsdh mjndggkb skkgc ngrnd stnjg scvzn kflk lxntzp vdhtgm rgc tdbcfb fhp dbdmmt jttxhczk pvtbbc tghpf tdtzd rdnhkqz kntss bnhmc dtdbzq mmpgm ssfvs rjt zhj vbb smzhj drsbbfrp hfkvr mmbqr xrtx smpsr lpznnc sp vfcs hjlcgk sxlj rtnjfqm hzvgn jcbcxtj jpvfc qmvp gdtq krjn vcx bpbdlmg nptr rhrrrbr jckpd (contains soy, peanuts, sesame)
lkkt htskk zjzrbq nmfv mxkh rtnjfqm md sp stnlkxj kflk lxntzp jdclr ldtr ldkmxjd vfgg rhcj xdkhj dpfvx mckrjz qqzjfk cztkz sxlj rgc kthm jvshst smpsr hkxqsc hchdc jpvfc tdbcfb cxjkfz tprcc tzvlm vxkmcmj gnsxbz qrcmm qtc lpznnc pvtbbc qtncs fnbqnt grsx sjzsc qvzxxp fnqxjl mbjt vrjj hzvgn mqxhh tghpf ngrnd cjbvz drsbbfrp krjn sxksnzf dtv bpbdlmg gkcqxs zjgxg dqgl kzcpb (contains peanuts)
kntss qgqnjx xvph xspm nhhsgd jdmhd gpgf dqgl csmfzp gkcqxs tzqrlb vfgg nptr hrbdk xbdsf mmpgm hskshtl lbgntt dtdbzq zxr qqzjfk cbmnb rdnhkqz hsrjm dgcq dfxmxd lpznnc hzvgn kkzqp qtc kthm jcbcxtj tdbcfb jpvfc rxpddd rhcj hdrkr zjgxg fczmkt mbjt cppzn nsvfk lgsk vfhlb mjjgj nzcgntfs ldkmxjd ngrnd qtncs mxkh jvshst dknlb nmfv sp xrrcj fnbqnt bvh qrcmm jchrn qrvft ktrfs bpbdlmg zjzrbq vxkmcmj scvzn krjn mgngg tdtzd jcmbbfm bhp (contains peanuts, nuts, shellfish)
cztkz dtv gnsxbz fhp vrjj hrbdk vfgg vzzgts ktrfs sgnq qtc hbmv cvcr dgcq kzcpb tprcc krjn lpznnc hdgfgj gxxg ctnkv rhrrrbr hjlcgk kkzqp dpfvx dxk gptcng htskk bpbdlmg csmfzp sjzsc ckqnfq qtncs cvpt xbdsf jjgvd qss mxkh ssfvs kfpx mpm qqzjfk bgsdh jchrn vbb mgngg bklcz bvh mckrjz qmgfs jvshst smzhj zgng gkcqxs bnhmc dqgl vxkmcmj lbgntt mvv rsrxtc zjzrbq stnjg gpgf rgc dtdbzq nzcgntfs lcc jpvfc pnkpb zfrlbz sp sjvrgx cvvjvf nsvfk md kqctvs cxjkfz tjpsn smpsr cppzn (contains nuts, dairy, peanuts)
fnbqnt mpm cnjmk pnkpb hrbdk xvph qqzjfk tdbcfb dpfvx vrjj rjt nzcgntfs qmvp krjn cvpt dfxmxd bklcz tk ngrnd vnqh hzvgn hkzrs nhhsgd zgng mft dtdbzq rxpddd bbmpr skkgc cpvn ctnkv kkzqp lcc gkcqxs qtncs jpvfc ldtr hfkvr fjk ssfvs bvh mjndggkb sxlj rhcj bn kntss mgngg gjck kflk sjvrgx kqctvs mqxhh pvtbbc bnsfss rgc qkdgt cppzn tzqrlb sp rrvp qtc hbmv mxkh drsbbfrp kfpx mjjgj gpgf tzvlm zjgxg (contains shellfish, fish)
mgngg vcx rgc nzcgntfs zjgxg rdnhkqz ctnkv fjk csmfzp hskshtl bnsfss ldg tk tjpsn mqxhh cvcr drsbbfrp vxkmcmj lkkt tprcc rjt md cztkz dpcmr kflk dknlb zhj kkzqp fnqxjl dtdbzq gkcqxs ldkmxjd ktrfs bbmpr dpfvx qtc cbmnb gxxg hzvgn sxlj tzqrlb jdclr fhp mft dbdmmt kzcpb cvpt czvhncg cnhch xtnk tdtzd hjlcgk sjvrgx bnhmc tdbcfb grsx mxkh htskk hdgfgj mmpgm bzfv hfkvr bklcz rsrxtc bpbdlmg dxk jjgvd qjgrv bvh jckpd rtnjfqm zjzrbq nmfv vfgg sp ckqnfq (contains nuts, sesame, fish)
rxpddd ktrfs hskshtl sp zfrlbz xnzqrm qgqnjx bvh ldkmxjd lxntzp hzvgn qjgrv tzvlm ngrnd ndgfp rgphsms zgng cxjkfz jpvfc rhcj bbxmx qtc htskk sjvrgx qmgfs kzcpb drsbbfrp rsrxtc mxkh cvcr sgnq bn tzqrlb ctnkv ldg vzzgts rgc kfsl skkgc hdgfgj rrvp tghpf jdclr rgdz nmfv tprcc cnjmk gdtq tdbcfb hkxqsc cztkz dfxmxd gkcqxs ndssl krjn lnhqgk jckpd mft cvpt (contains peanuts)
xnzqrm gkcqxs hjlcgk nhhsgd rgdz fhp qmgfs jcbcxtj jckpd ndssl qtncs xppdk mpm qtc xbmj fczmkt czvhncg mbjt kntss xdkhj drsbbfrp cvvjvf gdtq dtv vfcs bhp dqgl kbtxv mxkh cxjkfz tjpsn xvph cppzn bvh gnsxbz bnhmc rgc vnqh cbmnb gpgf smpsr tdbcfb pnkpb hkxqsc bzfv bn bpbdlmg scvzn zjgxg gxxg ngrnd ssfvs sgnq dfbnq hzvgn ctnkv jttxhczk nptr tk sp nmfv hbmv vrjj xbdsf (contains sesame, wheat)
ktrfs jvshst jchrn dbdmmt gxxg rjt kflk jjgvd vfgg vrjj rgc pxz sgn bzfv bhp grsx vxkmcmj qbbdfr fczmkt dqgl hchdc tdtzd xspm cnjmk hkzrs krjn rgdz hrbdk tjpsn mpm jpvfc cpvn kthm gkcqxs hvgh zbrfr bpbdlmg tdbcfb kkzqp qgqnjx cppzn hskshtl cvpt qvzxxp qtc kzcpb mckrjz sgnq gnsxbz mxkh nhhsgd qrcmm bvh qjgrv kfsl hzvgn fjk stnlkxj dfbnq vnqh (contains nuts, sesame)
ldtr kfsl mpm tzvlm tzqrlb mvv vnqh rjt drsbbfrp bbxmx bnhmc jchrn sxksnzf xbdsf kflk xnzqrm hbmv hsrjm sp ndssl bnsfss czvhncg sxlj bvh gdtq zgng bpbdlmg mmpgm vmhf zjgxg krjn nmfv hkxqsc qrcmm smzhj qmvp vfhlb gkcqxs dqgl xspm mxkh rdnhkqz fjk bhp nzcgntfs lpznnc qjgrv tdbcfb bklcz hskshtl qtncs lxntzp ktrfs gptcng ngrnd fczmkt dtdbzq md jpvfc smpsr tjpsn cbmnb (contains fish, nuts)
xdjt krjn xspm vbb htskk rgdz kqctvs cxjkfz hfkvr mqxhh pvtbbc rjt jvshst smpsr tghpf hsrjm sxksnzf vfhlb vcx qss tk qtncs clmg sp mvfqbnlb hzvgn bhp rgc nzcgntfs tdbcfb qtc czvhncg gkcqxs vnqh hkzrs mxkh qjgrv bpbdlmg nhhsgd gljdv qmgfs rtnjfqm cnhch vdhtgm mgngg ldtr rhcj vrjj dxk sgnq tdtzd (contains sesame)
vrjj zhj vfhlb smpsr xnzqrm ckqnfq bn czvhncg rjt cvcr bvh rdnhkqz kthm qrcmm md gnsxbz jvshst xspm cnjmk bpbdlmg pnkpb jckpd mxkh mft gxxg xtnk sp mmbqr rgphsms mqxhh kbtxv clmg tdbcfb qvzxxp bzfv lnhqgk xppdk zbrfr nzcgntfs jttxhczk mmpgm tzvlm vfcs kfsl ssfvs nmfv ldg ctnkv dknlb cvpt hbmv gljdv cpvn sxksnzf rgc jcbcxtj krjn xdjt bnsfss kzcpb cnhch hdrkr qtncs dfbnq cppzn hkxqsc sgnq zjgxg nsvfk jpvfc qtc (contains soy, sesame)
mvfqbnlb qjgrv bbmpr fjk vxkmcmj krjn hdgfgj sjvrgx ndgfp ctnkv gxxg cvvjvf mjjgj vrjj xrrcj skkgc sgnq hkxqsc hjlcgk cxjkfz qtncs sp cnhch vfgg bpbdlmg dknlb fnqxjl lpznnc dfxmxd mxkh qqzjfk bbxmx qgqnjx gkcqxs nzcgntfs zfrlbz rgphsms zgng hkzrs fczmkt rgc scvzn nmfv kqctvs tjpsn hrbdk tdbcfb ngrnd bhp sxksnzf rsrxtc hdrkr mmpgm jpvfc (contains peanuts)
mjjgj bzfv lxntzp xspm stnjg lgsk bbmpr jchrn qmvp mxkh xdjt rjt dknlb stnlkxj sxlj bn gkcqxs drsbbfrp ktrfs dtv htskk fhp tzvlm jjgvd bpbdlmg mtxxb rgc zxr mft jpvfc gxxg zfrlbz rxpddd kthm tdtzd vbb vxkmcmj bnsfss tdbcfb ldg fczmkt mgngg xvph mpm bvh ctnkv dgcq bbxmx kfsl pvtbbc hchdc sp qvzxxp hkzrs dpfvx mbjt (contains wheat, soy)
cjbvz rxpddd qbbdfr bpbdlmg tprcc ckqnfq vfcs qtc krjn lbgntt nptr vdhtgm xbdsf qss fczmkt mjndggkb dknlb tk ngrnd nsvfk sp hsrjm sjzsc csmfzp fnbqnt jckpd cnhch sxksnzf qqzjfk jpvfc gkcqxs nzcgntfs dtdbzq drsbbfrp dqgl mxkh gptcng hrbdk gpgf qgqnjx vzzgts xbmj jdclr lnhqgk mgngg smpsr rgc mjjgj mmpgm skkgc tdbcfb smzhj bbxmx cvpt qkdgt tdtzd qrcmm cppzn kqctvs ldtr vnqh vfgg qrvft xrtx kkzqp dgcq sjvrgx cxjkfz zjzrbq fnqxjl lgsk jcmbbfm stnjg bnhmc sgnq bnsfss zfrlbz gnsxbz czvhncg (contains wheat, dairy, soy)
rhrrrbr hbmv mtxxb hdrkr ndssl kfsl dgcq htskk mjjgj vmhf sjvrgx xnzqrm gptcng tzvlm jpvfc dpfvx xbdsf cnjmk jjgvd qkdgt xdkhj hkxqsc jckpd bvh rgdz dpcmr zgng hvgh dtv sp nptr tkqng cvvjvf cztkz cjbvz lgsk dtdbzq nsvfk grsx tdbcfb xbmj lxntzp vrjj bhp fnqxjl sxlj stnjg sjzsc mxkh cppzn hsrjm skkgc gkcqxs fnbqnt pnkpb krjn mjndggkb vbb sgn rgc nhhsgd vfgg dbdmmt ckqnfq ktrfs dknlb rhcj vdhtgm (contains wheat)
pxz fhp pnkpb xdjt bbmpr tdtzd rgc jchrn sp qvzxxp mxkh rxpddd hdgfgj tdbcfb grsx bvh czvhncg fnbqnt xspm vfgg zbrfr cbmnb hjlcgk bpbdlmg jttxhczk jdmhd hfkvr vzzgts krjn lgsk ktrfs dpfvx mpm mmpgm clmg jvshst sxksnzf jdclr jcbcxtj hrbdk bgsdh dknlb (contains soy, peanuts)
ldtr qbbdfr qtncs qmvp qrcmm skkgc rgc hkxqsc cpvn bn jjgvd hbmv krjn gpgf hskshtl kfpx tzqrlb fnqxjl rrvp tdbcfb fczmkt gkcqxs bklcz mjndggkb xrtx fnbqnt mmpgm mqxhh kntss ldg bpbdlmg mxkh xdjt tjpsn qrvft sp lkkt ktrfs mgngg hjlcgk xnzqrm tdtzd xppdk cppzn jpvfc vxkmcmj dpfvx ctnkv dxk (contains shellfish, fish, dairy)
xrrcj xdjt kntss fjk vrjj hzvgn gljdv mbjt zbrfr qkdgt kbtxv bpbdlmg kzcpb vdhtgm hdgfgj xppdk dfxmxd mtxxb qbbdfr jvshst ngrnd hbmv xspm fnqxjl mpm hkxqsc cjbvz tzqrlb cbmnb mxkh kkzqp tk bzfv vnqh bvh ldg nsvfk kthm qvzxxp jchrn rtnjfqm dpfvx mckrjz hdrkr gpgf dfbnq kfpx ndgfp jckpd stnjg kqctvs rgdz vmhf gkcqxs tdbcfb rdnhkqz bnhmc sp bnsfss cvcr cpvn krjn rrvp gdtq hjlcgk zjgxg ldkmxjd hchdc cxjkfz (contains wheat, shellfish)
rrvp ldkmxjd lcc hkzrs tk tghpf tdtzd cppzn vfcs mxkh hkxqsc xdkhj bhp kkzqp gljdv pxz drsbbfrp mjndggkb hdrkr jckpd xbmj kfsl hzvgn ssfvs sxlj xnzqrm nzcgntfs sgnq mjjgj jdmhd bpbdlmg pvtbbc mgngg rgc tkqng kzcpb vnqh krjn vbb qgqnjx kntss bzfv sjzsc rtnjfqm bnsfss bvh qss vdhtgm mqxhh zjzrbq dknlb vcx qjgrv mft vfhlb htskk nhhsgd qtncs vmhf gkcqxs gjck kfpx xdjt mmbqr xbdsf zgng qrvft md vrjj ctnkv cbmnb zjgxg qqzjfk lnhqgk ngrnd ldg sp kthm (contains peanuts)
ngrnd vfcs smzhj qbbdfr krjn hsrjm mgngg mqxhh jjgvd mtxxb nsvfk sp scvzn mjndggkb bvh bpbdlmg cnhch xdjt hfkvr cztkz rsrxtc mpm ldtr hdgfgj sjvrgx xppdk lpznnc rgc mft rdnhkqz qtc zxr tdtzd bbxmx gkcqxs cxjkfz mmpgm jchrn rhcj gptcng lxntzp smpsr vmhf zjzrbq hchdc jvshst mvv bzfv xrtx kzcpb vnqh pxz kflk nzcgntfs mxkh (contains soy, shellfish, nuts)
gptcng md hdgfgj mpm bpbdlmg lpznnc tk hchdc xrrcj tkqng tdbcfb rhcj kflk xdkhj dtdbzq zfrlbz ngrnd dfxmxd jpvfc mgngg xppdk xrtx gnsxbz csmfzp nptr qtncs vrjj bhp cztkz gpgf mjjgj bbxmx jchrn cnhch rdnhkqz vdhtgm gkcqxs lbgntt cpvn sxlj vmhf vzzgts sgn ndssl ndgfp qkdgt stnlkxj gxxg mxkh vcx qss mvv rgc smpsr sp cppzn kfpx fczmkt hvgh mckrjz sjzsc cxjkfz xbdsf bvh kbtxv cbmnb sjvrgx jdmhd jjgvd nsvfk mvfqbnlb jckpd dpcmr bbmpr dgcq (contains nuts)
"""