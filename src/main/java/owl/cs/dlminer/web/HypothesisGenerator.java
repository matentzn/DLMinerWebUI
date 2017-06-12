package owl.cs.dlminer.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import io.dlminer.learn.Hypothesis;
import io.dlminer.main.DLMiner;
import io.dlminer.main.DLMinerInput;
import io.dlminer.refine.OperatorConfig;

public class HypothesisGenerator {
	File of;
	DLMiner miner;
	HypothesisGenerator(File o) {
		this.of = o;
	}
	
	public void generate(int maxhypothesis, double minprecision, int max_concept_length, int minsupport) {
		InputStream ontologyFile = null;
        try {
        	ontologyFile = new FileInputStream(of);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        DLMinerInput input = new DLMinerInput(ontologyFile);
        input.setMaxHypothesesNumber(maxhypothesis);
        input.setMinPrecision(minprecision);

        OperatorConfig config = input.getConfig();
        config.maxLength = max_concept_length;
        config.minSupport = minsupport;

        this.miner = new DLMiner(input);
		try {
		    miner.init();
			miner.run();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<HypothesisI> getHypotheses() {
		List<HypothesisI> hypos = new ArrayList<>();
		
		for(Hypothesis h : miner.getOutput().getHypotheses()) {
			HypothesisI hyp = new HypthesisDefaultImpl(h);
			hypos.add(hyp);
		}
		return hypos;
	}

}
