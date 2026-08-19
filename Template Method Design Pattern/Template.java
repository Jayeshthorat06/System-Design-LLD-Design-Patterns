abstract class ModelTrainer
{
    public void load(String path)
    {
        System.out.println("[common] Load the data from the give path \t" + path);
    }

    public void preprocess()
    {
        System.out.println("[common] Preprocess the data according to the default Algorithm");
    }

    public abstract void train();
    public abstract void evaluate();
    
    public void save()
    {
        System.out.println("[common] Save the data to default directory");
    }

    final void TemplateMothod(String path)
    {
        load(path);
        preprocess();
        train();
        evaluate();
        save();
    }
}

class NeuralNetworkMethod extends ModelTrainer
{
    public void train()
    {
        System.out.println("[NeuralNetwork] Training the model according to the Neural ML method");
    }

    public void evaluate()
    {
        System.out.println("[NeuralNetwork] Evaluating the data of Neural Network");
    }
}

class DecisionMethod extends ModelTrainer
{
    public void train()
    {
        System.out.println("[Decision] Training the model according to the Decision ML method");
    }

    public void evaluate()
    {
        System.out.println("[Decision] Evaluating the data of Decision Method");
    }

    public void save()
    {
        System.out.println("[Decision] Save the evaluated data to vector db");
    }
}

class Template
{
    public static void main(String[] args) {
        ModelTrainer m1 = new NeuralNetworkMethod();
        m1.TemplateMothod("C\\Jayesh\\Neural");

        ModelTrainer m2 = new DecisionMethod();
        m2.TemplateMothod("Take from Database Query Select * from Model");
    }
}