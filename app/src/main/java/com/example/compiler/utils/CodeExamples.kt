package com.example.compiler.utils

data class Example(
    val name: String,
    val code: String
)

object CodeExamples {
    val examples = listOf(
        // Original Examples
        Example(
            name = "Hello World",
            code = """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Hello, World!");
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Sum Two Numbers",
            code = """
                public class Main {
                    public static void main(String[] args) {
                        int a = 10;
                        int b = 20;
                        int sum = a + b;
                        System.out.println("Sum: " + sum);
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Fibonacci",
            code = """
                public class Main {
                    public static int fib(int n) {
                        if (n < 2) return n;
                        return fib(n - 1) + fib(n - 2);
                    }
                    
                    public static void main(String[] args) {
                        System.out.println("Fib(10) = " + fib(10));
                    }
                }
            """.trimIndent()
        ),

        // AI Theme (4 examples)
        Example(
            name = "Simple Perceptron",
            code = """
                public class Main {
                    public static double predict(double[] inputs, double[] weights) {
                        double sum = 0;
                        for (int i = 0; i < inputs.length; i++) {
                            sum += inputs[i] * weights[i];
                        }
                        return sum >= 0 ? 1 : 0;
                    }
                    
                    public static void main(String[] args) {
                        double[] inputs = {1, 0};
                        double[] weights = {0.5, -0.5};
                        System.out.println("Prediction: " + predict(inputs, weights));
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Linear Regression",
            code = """
                public class Main {
                    public static double predict(double x, double slope, double intercept) {
                        return slope * x + intercept;
                    }
                    
                    public static void main(String[] args) {
                        double slope = 2.0;
                        double intercept = 1.0;
                        double x = 3.0;
                        System.out.println("Y = " + predict(x, slope, intercept));
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Distance Calculator",
            code = """
                public class Main {
                    public static double euclideanDistance(double[] p1, double[] p2) {
                        double sum = 0;
                        for (int i = 0; i < p1.length; i++) {
                            sum += Math.pow(p1[i] - p2[i], 2);
                        }
                        return Math.sqrt(sum);
                    }
                    
                    public static void main(String[] args) {
                        double[] p1 = {1, 2};
                        double[] p2 = {4, 6};
                        System.out.println("Distance: " + euclideanDistance(p1, p2));
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Activation Function",
            code = """
                public class Main {
                    public static double sigmoid(double x) {
                        return 1.0 / (1.0 + Math.exp(-x));
                    }
                    
                    public static void main(String[] args) {
                        double input = 0.5;
                        System.out.println("Sigmoid: " + sigmoid(input));
                    }
                }
            """.trimIndent()
        ),

        // Web Theme (4 examples)
        Example(
            name = "Simple Server",
            code = """
                import java.net.*;
                import java.io.*;
                
                public class Main {
                    public static void main(String[] args) throws IOException {
                        ServerSocket server = new ServerSocket(8080);
                        System.out.println("Server started on port 8080");
                        Socket client = server.accept();
                        PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                        out.println("Hello from server!");
                        client.close();
                        server.close();
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "URL Reader",
            code = """
                import java.net.*;
                import java.io.*;
                
                public class Main {
                    public static void main(String[] args) throws Exception {
                        URL url = new URL("https://example.com");
                        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
                        String line;
                        while ((line = in.readLine()) != null) {
                            System.out.println(line);
                        }
                        in.close();
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "HTTP Status Checker",
            code = """
                import java.net.*;
                
                public class Main {
                    public static int getStatus(String urlString) throws Exception {
                        URL url = new URL(urlString);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        return conn.getResponseCode();
                    }
                    
                    public static void main(String[] args) throws Exception {
                        System.out.println("Status: " + getStatus("https://example.com"));
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Query Parser",
            code = """
                public class Main {
                    public static String parseQuery(String query) {
                        String[] parts = query.split("&");
                        StringBuilder result = new StringBuilder();
                        for (String part : parts) {
                            result.append(part.split("=")[0]).append(": ").append(part.split("=")[1]).append("\n");
                        }
                        return result.toString();
                    }
                    
                    public static void main(String[] args) {
                        String query = "name=John&age=30";
                        System.out.println(parseQuery(query));
                    }
                }
            """.trimIndent()
        ),

        // Electronics Theme (4 examples)
        Example(
            name = "Ohm's Law",
            code = """
                public class Main {
                    public static double calculateCurrent(double voltage, double resistance) {
                        return voltage / resistance;
                    }
                    
                    public static void main(String[] args) {
                        double v = 12.0; // volts
                        double r = 4.0;  // ohms
                        System.out.println("Current: " + calculateCurrent(v, r) + " A");
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "LED Resistor",
            code = """
                public class Main {
                    public static double calculateResistor(double supplyV, double ledV, double current) {
                        return (supplyV - ledV) / current;
                    }
                    
                    public static void main(String[] args) {
                        double supply = 5.0;  // volts
                        double led = 2.0;     // volts
                        double i = 0.02;      // amps
                        System.out.println("Resistor: " + calculateResistor(supply, led, i) + " ohms");
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Capacitor Charge",
            code = """
                public class Main {
                    public static double charge(double capacitance, double voltage) {
                        return capacitance * voltage;
                    }
                    
                    public static void main(String[] args) {
                        double c = 0.0001; // farads
                        double v = 9.0;    // volts
                        System.out.println("Charge: " + charge(c, v) + " coulombs");
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Frequency Calculator",
            code = """
                public class Main {
                    public static double calculateFrequency(double inductance, double capacitance) {
                        return 1 / (2 * Math.PI * Math.sqrt(inductance * capacitance));
                    }
                    
                    public static void main(String[] args) {
                        double l = 0.1;  // henries
                        double c = 0.000001; // farads
                        System.out.println("Frequency: " + calculateFrequency(l, c) + " Hz");
                    }
                }
            """.trimIndent()
        ),

        // Space Theme (4 examples)
        Example(
            name = "Orbital Velocity",
            code = """
                public class Main {
                    public static double orbitalVelocity(double mass, double radius) {
                        double G = 6.67430e-11; // gravitational constant
                        return Math.sqrt(G * mass / radius);
                    }
                    
                    public static void main(String[] args) {
                        double earthMass = 5.972e24; // kg
                        double radius = 6.371e6;     // meters
                        System.out.println("Velocity: " + orbitalVelocity(earthMass, radius) + " m/s");
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Escape Velocity",
            code = """
                public class Main {
                    public static double escapeVelocity(double mass, double radius) {
                        double G = 6.67430e-11;
                        return Math.sqrt(2 * G * mass / radius);
                    }
                    
                    public static void main(String[] args) {
                        double moonMass = 7.342e22; // kg
                        double radius = 1.738e6;    // meters
                        System.out.println("Escape Velocity: " + escapeVelocity(moonMass, radius) + " m/s");
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Kepler's Third Law",
            code = """
                public class Main {
                    public static double orbitalPeriod(double mass, double semiMajorAxis) {
                        double G = 6.67430e-11;
                        return 2 * Math.PI * Math.sqrt(Math.pow(semiMajorAxis, 3) / (G * mass));
                    }
                    
                    public static void main(String[] args) {
                        double sunMass = 1.989e30; // kg
                        double axis = 1.496e11;    // meters
                        System.out.println("Period: " + orbitalPeriod(sunMass, axis) + " seconds");
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Light Year Converter",
            code = """
                public class Main {
                    public static double toLightYears(double meters) {
                        return meters / 9.461e15;
                    }
                    
                    public static void main(String[] args) {
                        double distance = 4.068e16; // meters
                        System.out.println("Light Years: " + toLightYears(distance));
                    }
                }
            """.trimIndent()
        ),

        // Game Development Theme (4 examples)
        Example(
            name = "Simple Game Loop",
            code = """
                public class Main {
                    public static void main(String[] args) throws InterruptedException {
                        int frame = 0;
                        while (frame < 10) {
                            System.out.println("Frame: " + frame);
                            Thread.sleep(1000); // 1 second per frame
                            frame++;
                        }
                        System.out.println("Game Over!");
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Player Movement",
            code = """
                public class Main {
                    static class Player {
                        int x, y;
                        Player(int x, int y) { this.x = x; this.y = y; }
                        void move(int dx, int dy) { x += dx; y += dy; }
                    }
                    
                    public static void main(String[] args) {
                        Player player = new Player(0, 0);
                        player.move(3, 4);
                        System.out.println("Position: (" + player.x + ", " + player.y + ")");
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Collision Detection",
            code = """
                public class Main {
                    static boolean isColliding(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
                        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
                    }
                    
                    public static void main(String[] args) {
                        boolean collision = isColliding(0, 0, 10, 10, 5, 5, 10, 10);
                        System.out.println("Collision: " + collision);
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Score Tracker",
            code = """
                public class Main {
                    static class Game {
                        int score = 0;
                        void addPoints(int points) { score += points; }
                        int getScore() { return score; }
                    }
                    
                    public static void main(String[] args) {
                        Game game = new Game();
                        game.addPoints(100);
                        game.addPoints(50);
                        System.out.println("Total Score: " + game.getScore());
                    }
                }
            """.trimIndent()
        ),
        Example(
            name = "Neural Network Layer",
            code = """
        public class Main {
            static double[] forward(double[] inputs, double[][] weights) {
                double[] outputs = new double[weights.length];
                for (int i = 0; i < weights.length; i++) {
                    double sum = 0;
                    for (int j = 0; j < inputs.length; j++) {
                        sum += inputs[j] * weights[i][j];
                    }
                    outputs[i] = sum > 0 ? 1 : 0;
                }
                return outputs;
            }
            
            public static void main(String[] args) {
                double[] inputs = {1, 0};
                double[][] weights = {{0.5, -0.5}, {0.3, 0.7}};
                double[] result = forward(inputs, weights);
                System.out.println("Output: [" + result[0] + ", " + result[1] + "]");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "K-Means Centroid",
            code = """
        public class Main {
            static double[] calculateCentroid(double[][] points) {
                double[] centroid = new double[2];
                for (double[] point : points) {
                    centroid[0] += point[0];
                    centroid[1] += point[1];
                }
                centroid[0] /= points.length;
                centroid[1] /= points.length;
                return centroid;
            }
            
            public static void main(String[] args) {
                double[][] points = {{1, 2}, {2, 3}, {3, 4}};
                double[] centroid = calculateCentroid(points);
                System.out.println("Centroid: (" + centroid[0] + ", " + centroid[1] + ")");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Socket Client",
            code = """
        import java.io.*;
        import java.net.*;
        
        public class Main {
            public static void main(String[] args) throws Exception {
                Socket socket = new Socket("localhost", 8080);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out.println("Hello Server!");
                System.out.println("Server says: " + in.readLine());
                socket.close();
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "HTML Parser",
            code = """
        public class Main {
            static String extractTag(String html, String tag) {
                String openTag = "<" + tag + ">";
                String closeTag = "</" + tag + ">";
                int start = html.indexOf(openTag) + openTag.length();
                int end = html.indexOf(closeTag);
                return html.substring(start, end);
            }
            
            public static void main(String[] args) {
                String html = "<p>Hello World</p>";
                System.out.println("Content: " + extractTag(html, "p"));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Circuit Power",
            code = """
        public class Main {
            static double calculatePower(double voltage, double current) {
                return voltage * current;
            }
            
            public static void main(String[] args) {
                double v = 9.0;  // volts
                double i = 0.5;  // amps
                System.out.println("Power: " + calculatePower(v, i) + " watts");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Transistor Gain",
            code = """
        public class Main {
            static double calculateGain(double collectorCurrent, double baseCurrent) {
                return collectorCurrent / baseCurrent;
            }
            
            public static void main(String[] args) {
                double ic = 0.1;  // amps
                double ib = 0.001; // amps
                System.out.println("Gain: " + calculateGain(ic, ib));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Rocket Thrust",
            code = """
        public class Main {
            static double calculateThrust(double massFlow, double exhaustVelocity) {
                return massFlow * exhaustVelocity;
            }
            
            public static void main(String[] args) {
                double mdot = 10.0;  // kg/s
                double ve = 3000.0;  // m/s
                System.out.println("Thrust: " + calculateThrust(mdot, ve) + " N");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Star Distance",
            code = """
        public class Main {
            static double parallaxToDistance(double parallaxAngle) {
                return 1.0 / parallaxAngle; // parsecs
            }
            
            public static void main(String[] args) {
                double angle = 0.5;  // arcseconds
                System.out.println("Distance: " + parallaxToDistance(angle) + " parsecs");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Enemy AI",
            code = """
        public class Main {
            static class Enemy {
                int x, y;
                Enemy(int x, int y) { this.x = x; this.y = y; }
                void chase(int playerX, int playerY) {
                    if (playerX > x) x++;
                    if (playerX < x) x--;
                    if (playerY > y) y++;
                    if (playerY < y) y--;
                }
            }
            
            public static void main(String[] args) {
                Enemy enemy = new Enemy(0, 0);
                enemy.chase(3, 4);
                System.out.println("Enemy at: (" + enemy.x + ", " + enemy.y + ")");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Random Level Generator",
            code = """
        import java.util.Random;
        
        public class Main {
            static String generateLevel(int size) {
                Random rand = new Random();
                StringBuilder level = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    level.append(rand.nextBoolean() ? "#" : ".");
                }
                return level.toString();
            }
            
            public static void main(String[] args) {
                System.out.println("Level: " + generateLevel(10));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Decision Tree",
            code = """
        public class Main {
            static String decide(int health, int ammo) {
                if (health > 50) {
                    if (ammo > 10) return "Attack";
                    return "Search for ammo";
                }
                return "Retreat";
            }
            
            public static void main(String[] args) {
                System.out.println("Decision: " + decide(75, 5));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Form Validator",
            code = """
        public class Main {
            static boolean isValidEmail(String email) {
                return email.contains("@") && email.contains(".");
            }
            
            public static void main(String[] args) {
                String email = "user@example.com";
                System.out.println("Valid Email: " + isValidEmail(email));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Voltage Divider",
            code = """
        public class Main {
            static double calculateOutput(double vIn, double r1, double r2) {
                return vIn * (r2 / (r1 + r2));
            }
            
            public static void main(String[] args) {
                double vin = 12.0;  // volts
                double r1 = 1000.0; // ohms
                double r2 = 2000.0; // ohms
                System.out.println("Output Voltage: " + calculateOutput(vin, r1, r2) + " V");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Gravity Simulator",
            code = """
        public class Main {
            static double gravitationalForce(double m1, double m2, double r) {
                double G = 6.67430e-11;
                return G * m1 * m2 / (r * r);
            }
            
            public static void main(String[] args) {
                double m1 = 5.972e24; // Earth kg
                double m2 = 7.342e22; // Moon kg
                double r = 3.844e8;   // meters
                System.out.println("Force: " + gravitationalForce(m1, m2, r) + " N");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Timer",
            code = """
        public class Main {
            static class Timer {
                long startTime;
                Timer() { startTime = System.currentTimeMillis(); }
                double elapsed() { return (System.currentTimeMillis() - startTime) / 1000.0; }
            }
            
            public static void main(String[] args) throws InterruptedException {
                Timer timer = new Timer();
                Thread.sleep(2000);
                System.out.println("Elapsed Time: " + timer.elapsed() + " seconds");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Pathfinding Cost",
            code = """
        public class Main {
            static int manhattanDistance(int x1, int y1, int x2, int y2) {
                return Math.abs(x1 - x2) + Math.abs(y1 - y2);
            }
            
            public static void main(String[] args) {
                int cost = manhattanDistance(0, 0, 3, 4);
                System.out.println("Path Cost: " + cost);
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Cookie Parser",
            code = """
        public class Main {
            static String getCookieValue(String cookies, String key) {
                String[] pairs = cookies.split("; ");
                for (String pair : pairs) {
                    if (pair.startsWith(key + "=")) {
                        return pair.substring(key.length() + 1);
                    }
                }
                return null;
            }
            
            public static void main(String[] args) {
                String cookies = "user=John; age=30";
                System.out.println("User: " + getCookieValue(cookies, "user"));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Signal Attenuation",
            code = """
        public class Main {
            static double calculateAttenuation(double powerIn, double distance) {
                return powerIn / (distance * distance);
            }
            
            public static void main(String[] args) {
                double pin = 100.0;  // watts
                double d = 10.0;     // meters
                System.out.println("Power Out: " + calculateAttenuation(pin, d) + " watts");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Orbit Altitude",
            code = """
        public class Main {
            static double geoAltitude(double period) {
                double G = 6.67430e-11;
                double M = 5.972e24; // Earth mass
                double factor = (period / (2 * Math.PI)) * (period / (2 * Math.PI)) * G * M;
                return Math.cbrt(factor) - 6.371e6; // subtract Earth radius
            }
            
            public static void main(String[] args) {
                double period = 86164; // seconds (geosynchronous)
                System.out.println("Altitude: " + geoAltitude(period) + " meters");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Health System",
            code = """
        public class Main {
            static class Player {
                int health = 100;
                void takeDamage(int damage) { health = Math.max(0, health - damage); }
                boolean isAlive() { return health > 0; }
            }
            
            public static void main(String[] args) {
                Player player = new Player();
                player.takeDamage(30);
                System.out.println("Health: " + player.health + ", Alive: " + player.isAlive());
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Gradient Descent",
            code = """
        public class Main {
            static double gradientDescent(double x, double y, double learningRate) {
                double prediction = 2 * x; // Simple model: y = 2x
                double error = prediction - y;
                return x - learningRate * error * 2; // Update x
            }
            
            public static void main(String[] args) {
                double x = 1.0, y = 4.0;
                double newX = gradientDescent(x, y, 0.1);
                System.out.println("Updated x: " + newX);
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Softmax",
            code = """
        public class Main {
            static double[] softmax(double[] inputs) {
                double sum = 0;
                double[] result = new double[inputs.length];
                for (double input : inputs) sum += Math.exp(input);
                for (int i = 0; i < inputs.length; i++) {
                    result[i] = Math.exp(inputs[i]) / sum;
                }
                return result;
            }
            
            public static void main(String[] args) {
                double[] inputs = {1.0, 2.0, 3.0};
                double[] probs = softmax(inputs);
                System.out.println("Probabilities: [" + probs[0] + ", " + probs[1] + ", " + probs[2] + "]");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Feature Scaling",
            code = """
        public class Main {
            static double[] normalize(double[] values) {
                double min = Double.MAX_VALUE, max = Double.MIN_VALUE;
                for (double v : values) {
                    min = Math.min(min, v);
                    max = Math.max(max, v);
                }
                double[] result = new double[values.length];
                for (int i = 0; i < values.length; i++) {
                    result[i] = (values[i] - min) / (max - min);
                }
                return result;
            }
            
            public static void main(String[] args) {
                double[] data = {1, 5, 10};
                double[] norm = normalize(data);
                System.out.println("Normalized: [" + norm[0] + ", " + norm[1] + ", " + norm[2] + "]");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Loss Function",
            code = """
        public class Main {
            static double meanSquaredError(double[] actual, double[] predicted) {
                double sum = 0;
                for (int i = 0; i < actual.length; i++) {
                    sum += Math.pow(actual[i] - predicted[i], 2);
                }
                return sum / actual.length;
            }
            
            public static void main(String[] args) {
                double[] actual = {1, 2, 3};
                double[] predicted = {1.1, 1.9, 3.2};
                System.out.println("MSE: " + meanSquaredError(actual, predicted));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Random Weights",
            code = """
        import java.util.Random;
        
        public class Main {
            static double[] initWeights(int size) {
                Random rand = new Random();
                double[] weights = new double[size];
                for (int i = 0; i < size; i++) {
                    weights[i] = rand.nextDouble() - 0.5; // Range: -0.5 to 0.5
                }
                return weights;
            }
            
            public static void main(String[] args) {
                double[] weights = initWeights(3);
                System.out.println("Weights: [" + weights[0] + ", " + weights[1] + ", " + weights[2] + "]");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Matrix Multiply",
            code = """
        public class Main {
            static double[] matrixMultiply(double[] vector, double[][] matrix) {
                double[] result = new double[matrix.length];
                for (int i = 0; i < matrix.length; i++) {
                    double sum = 0;
                    for (int j = 0; j < vector.length; j++) {
                        sum += vector[j] * matrix[i][j];
                    }
                    result[i] = sum;
                }
                return result;
            }
            
            public static void main(String[] args) {
                double[] vec = {1, 2};
                double[][] mat = {{1, 0}, {0, 1}};
                double[] res = matrixMultiply(vec, mat);
                System.out.println("Result: [" + res[0] + ", " + res[1] + "]");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Base64 Encoder",
            code = """
        import java.util.Base64;
        
        public class Main {
            static String encode(String input) {
                return Base64.getEncoder().encodeToString(input.getBytes());
            }
            
            public static void main(String[] args) {
                String text = "Hello Web";
                System.out.println("Encoded: " + encode(text));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web JSON Parser",
            code = """
        public class Main {
            static String getValue(String json, String key) {
                String search = "\"" + key + "\":\"";
                int start = json.indexOf(search) + search.length();
                int end = json.indexOf("\"", start);
                return json.substring(start, end);
            }
            
            public static void main(String[] args) {
                String json = "{\"name\":\"John\",\"age\":\"30\"}";
                System.out.println("Name: " + getValue(json, "name"));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Port Scanner",
            code = """
        import java.net.*;
        
        public class Main {
            static boolean isPortOpen(String host, int port) {
                try (Socket socket = new Socket()) {
                    socket.connect(new InetSocketAddress(host, port), 1000);
                    return true;
                } catch (Exception e) {
                    return false;
                }
            }
            
            public static void main(String[] args) {
                System.out.println("Port 80 open: " + isPortOpen("localhost", 80));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web IP Resolver",
            code = """
        import java.net.*;
        
        public class Main {
            static String getIP(String hostname) throws Exception {
                return InetAddress.getByName(hostname).getHostAddress();
            }
            
            public static void main(String[] args) throws Exception {
                System.out.println("IP: " + getIP("example.com"));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web MIME Detector",
            code = """
        public class Main {
            static String getMimeType(String filename) {
                if (filename.endsWith(".html")) return "text/html";
                if (filename.endsWith(".jpg")) return "image/jpeg";
                return "application/octet-stream";
            }
            
            public static void main(String[] args) {
                System.out.println("MIME: " + getMimeType("index.html"));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Redirect Simulator",
            code = """
        public class Main {
            static String redirect(String url) {
                if (url.startsWith("http://")) return "https://" + url.substring(7);
                return url;
            }
            
            public static void main(String[] args) {
                String url = "http://example.com";
                System.out.println("Redirected: " + redirect(url));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics RC Time",
            code = """
        public class Main {
            static double rcTimeConstant(double resistance, double capacitance) {
                return resistance * capacitance;
            }
            
            public static void main(String[] args) {
                double r = 1000.0;  // ohms
                double c = 0.0001;  // farads
                System.out.println("Time Constant: " + rcTimeConstant(r, c) + " seconds");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Watt Converter",
            code = """
        public class Main {
            static double toDBm(double watts) {
                return 10 * Math.log10(watts * 1000);
            }
            
            public static void main(String[] args) {
                double power = 0.1;  // watts
                System.out.println("Power: " + toDBm(power) + " dBm");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Diode Drop",
            code = """
        public class Main {
            static double outputVoltage(double input, double drop) {
                return Math.max(0, input - drop);
            }
            
            public static void main(String[] args) {
                double vin = 5.0;   // volts
                double drop = 0.7;  // volts (silicon diode)
                System.out.println("Output: " + outputVoltage(vin, drop) + " V");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics PWM Duty",
            code = """
        public class Main {
            static double dutyCycle(double onTime, double period) {
                return (onTime / period) * 100;
            }
            
            public static void main(String[] args) {
                double on = 2.0;   // ms
                double period = 10.0; // ms
                System.out.println("Duty Cycle: " + dutyCycle(on, period) + "%");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Heat Dissipation",
            code = """
        public class Main {
            static double heat(double power, double time) {
                return power * time; // Energy in joules
            }
            
            public static void main(String[] args) {
                double p = 10.0;  // watts
                double t = 60.0;  // seconds
                System.out.println("Heat: " + heat(p, t) + " joules");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Filter Cutoff",
            code = """
        public class Main {
            static double cutoffFrequency(double r, double c) {
                return 1 / (2 * Math.PI * r * c);
            }
            
            public static void main(String[] args) {
                double r = 1000.0;  // ohms
                double c = 0.000001; // farads
                System.out.println("Cutoff: " + cutoffFrequency(r, c) + " Hz");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Delta-V",
            code = """
        public class Main {
            static double deltaV(double isp, double m0, double mf) {
                double g = 9.81; // m/s^2
                return isp * g * Math.log(m0 / mf);
            }
            
            public static void main(String[] args) {
                double isp = 300.0;  // seconds
                double m0 = 1000.0;  // kg (initial mass)
                double mf = 500.0;   // kg (final mass)
                System.out.println("Delta-V: " + deltaV(isp, m0, mf) + " m/s");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Apoapsis",
            code = """
        public class Main {
            static double apoapsis(double v, double r, double mu) {
                double a = (mu * r) / (2 * mu - v * v * r);
                return 2 * a - r;
            }
            
            public static void main(String[] args) {
                double v = 7.8e3;     // m/s
                double r = 6.371e6;   // m
                double mu = 3.986e14; // m^3/s^2
                System.out.println("Apoapsis: " + apoapsis(v, r, mu) + " m");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Hohmann Transfer",
            code = """
        public class Main {
            static double deltaV1(double r1, double r2, double mu) {
                return Math.sqrt(mu / r1) * (Math.sqrt(2 * r2 / (r1 + r2)) - 1);
            }
            
            public static void main(String[] args) {
                double r1 = 6.371e6;   // m
                double r2 = 4.2164e7;  // m
                double mu = 3.986e14;  // m^3/s^2
                System.out.println("Delta-V1: " + deltaV1(r1, r2, mu) + " m/s");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Redshift",
            code = """
        public class Main {
            static double redshift(double v, double c) {
                return v / c; // Simplified non-relativistic
            }
            
            public static void main(String[] args) {
                double v = 30000.0;  // m/s
                double c = 3.0e8;    // m/s
                System.out.println("Redshift: " + redshift(v, c));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Solar Power",
            code = """
        public class Main {
            static double powerReceived(double power, double r) {
                return power / (4 * Math.PI * r * r);
            }
            
            public static void main(String[] args) {
                double p = 3.828e26;  // watts (Sun)
                double r = 1.496e11;  // meters (Earth-Sun)
                System.out.println("Power: " + powerReceived(p, r) + " W/m^2");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Eccentricity",
            code = """
        public class Main {
            static double eccentricity(double ra, double rp) {
                return (ra - rp) / (ra + rp);
            }
            
            public static void main(String[] args) {
                double ra = 7.0e6;  // apoapsis m
                double rp = 6.5e6;  // periapsis m
                System.out.println("Eccentricity: " + eccentricity(ra, rp));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Projectile",
            code = """
        public class Main {
            static class Projectile {
                double x, y, vx, vy;
                Projectile(double x, double y, double angle, double speed) {
                    this.x = x; this.y = y;
                    this.vx = speed * Math.cos(angle);
                    this.vy = speed * Math.sin(angle);
                }
                void update(double dt) { x += vx * dt; y += vy * dt; }
            }
            
            public static void main(String[] args) {
                Projectile p = new Projectile(0, 0, Math.PI / 4, 10);
                p.update(1.0);
                System.out.println("Position: (" + p.x + ", " + p.y + ")");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Inventory",
            code = """
        import java.util.ArrayList;
        
        public class Main {
            static class Inventory {
                ArrayList<String> items = new ArrayList<>();
                void add(String item) { items.add(item); }
                String list() { return String.join(", ", items); }
            }
            
            public static void main(String[] args) {
                Inventory inv = new Inventory();
                inv.add("Sword");
                inv.add("Shield");
                System.out.println("Items: " + inv.list());
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Random Enemy",
            code = """
        import java.util.Random;
        
        public class Main {
            static class Enemy {
                String type;
                Enemy() {
                    String[] types = {"Goblin", "Orc", "Troll"};
                    type = types[new Random().nextInt(types.length)];
                }
            }
            
            public static void main(String[] args) {
                Enemy enemy = new Enemy();
                System.out.println("Enemy: " + enemy.type);
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Cooldown",
            code = """
        public class Main {
            static class Ability {
                long lastUsed;
                int cooldown; // ms
                Ability(int cd) { cooldown = cd; lastUsed = 0; }
                boolean canUse(long now) {
                    return (now - lastUsed) >= cooldown;
                }
                void use(long now) { lastUsed = now; }
            }
            
            public static void main(String[] args) {
                Ability ability = new Ability(2000);
                long now = System.currentTimeMillis();
                System.out.println("Can use: " + ability.canUse(now));
                ability.use(now);
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Raycasting",
            code = """
        public class Main {
            static double castRay(double x, double y, double angle, double[][] walls) {
                double dx = Math.cos(angle), dy = Math.sin(angle);
                for (double t = 0; t < 100; t += 0.1) {
                    double px = x + dx * t, py = y + dy * t;
                    for (double[] wall : walls) {
                        if (px >= wall[0] && px <= wall[2] && py >= wall[1] && py <= wall[3]) {
                            return t;
                        }
                    }
                }
                return -1;
            }
            
            public static void main(String[] args) {
                double[][] walls = {{2, 2, 2, 4}};
                double dist = castRay(0, 0, Math.PI / 4, walls);
                System.out.println("Distance: " + dist);
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game State Machine",
            code = """
        public class Main {
            static class GameState {
                String state = "Menu";
                void transition(String input) {
                    if (state.equals("Menu") && input.equals("start")) state = "Playing";
                    else if (state.equals("Playing") && input.equals("quit")) state = "Menu";
                }
            }
            
            public static void main(String[] args) {
                GameState game = new GameState();
                game.transition("start");
                System.out.println("State: " + game.state);
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI ReLU Activation",
            code = """
        public class Main {
            static double relu(double x) {
                return Math.max(0, x);
            }
            
            public static void main(String[] args) {
                double input = -1.5;
                System.out.println("ReLU: " + relu(input));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Confusion Matrix",
            code = """
        public class Main {
            static int truePositives(int[] actual, int[] predicted) {
                int tp = 0;
                for (int i = 0; i < actual.length; i++) {
                    if (actual[i] == 1 && predicted[i] == 1) tp++;
                }
                return tp;
            }
            
            public static void main(String[] args) {
                int[] actual = {1, 0, 1, 1};
                int[] predicted = {1, 0, 0, 1};
                System.out.println("True Positives: " + truePositives(actual, predicted));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Learning Rate Decay",
            code = """
        public class Main {
            static double decay(double initialRate, int epoch, double decayRate) {
                return initialRate / (1 + decayRate * epoch);
            }
            
            public static void main(String[] args) {
                double rate = 0.1;
                int epoch = 5;
                double decay = 0.1;
                System.out.println("Decayed Rate: " + decay(rate, epoch, decay));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI One-Hot Encoding",
            code = """
        public class Main {
            static int[] oneHot(int value, int size) {
                int[] encoding = new int[size];
                encoding[value] = 1;
                return encoding;
            }
            
            public static void main(String[] args) {
                int[] encoded = oneHot(2, 5);
                System.out.print("One-Hot: [");
                for (int i : encoded) System.out.print(i + " ");
                System.out.println("]");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web URL Encoder",
            code = """
        import java.net.URLEncoder;
        import java.nio.charset.StandardCharsets;
        
        public class Main {
            static String encode(String input) {
                return URLEncoder.encode(input, StandardCharsets.UTF_8);
            }
            
            public static void main(String[] args) {
                String text = "Hello Web!";
                System.out.println("Encoded: " + encode(text));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Header Parser",
            code = """
        public class Main {
            static String getHeader(String headers, String key) {
                String[] lines = headers.split("\n");
                for (String line : lines) {
                    if (line.startsWith(key + ":")) {
                        return line.substring(key.length() + 1).trim();
                    }
                }
                return null;
            }
            
            public static void main(String[] args) {
                String headers = "Content-Type: text/html\nServer: Apache";
                System.out.println("Content-Type: " + getHeader(headers, "Content-Type"));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Bandwidth Calc",
            code = """
        public class Main {
            static double transferTime(double size, double speed) {
                return size / speed; // size in MB, speed in MB/s
            }
            
            public static void main(String[] args) {
                double fileSize = 100.0; // MB
                double bandwidth = 10.0; // MB/s
                System.out.println("Transfer Time: " + transferTime(fileSize, bandwidth) + " seconds");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Cache Simulator",
            code = """
        import java.util.HashMap;
        
        public class Main {
            static class Cache {
                HashMap<String, String> store = new HashMap<>();
                void put(String key, String value) { store.put(key, value); }
                String get(String key) { return store.getOrDefault(key, "Not found"); }
            }
            
            public static void main(String[] args) {
                Cache cache = new Cache();
                cache.put("page1", "Content");
                System.out.println("Cached: " + cache.get("page1"));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Parallel Resistance",
            code = """
        public class Main {
            static double parallel(double r1, double r2) {
                return 1 / (1/r1 + 1/r2);
            }
            
            public static void main(String[] args) {
                double r1 = 1000.0; // ohms
                double r2 = 2000.0; // ohms
                System.out.println("Parallel Resistance: " + parallel(r1, r2) + " ohms");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Battery Life",
            code = """
        public class Main {
            static double batteryLife(double capacity, double current) {
                return capacity / current; // mAh, mA -> hours
            }
            
            public static void main(String[] args) {
                double cap = 2000.0; // mAh
                double curr = 100.0; // mA
                System.out.println("Battery Life: " + batteryLife(cap, curr) + " hours");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Inductor Energy",
            code = """
        public class Main {
            static double energy(double inductance, double current) {
                return 0.5 * inductance * current * current; // joules
            }
            
            public static void main(String[] args) {
                double l = 0.1;  // henries
                double i = 2.0;  // amps
                System.out.println("Energy: " + energy(l, i) + " joules");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Signal Delay",
            code = """
        public class Main {
            static double delay(double length, double speed) {
                return length / speed; // meters, m/s -> seconds
            }
            
            public static void main(String[] args) {
                double len = 1000.0;  // meters
                double v = 2.0e8;     // m/s (approx in cable)
                System.out.println("Delay: " + delay(len, v) + " seconds");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Orbital Energy",
            code = """
        public class Main {
            static double orbitalEnergy(double v, double r, double mu) {
                return (v * v / 2) - (mu / r); // specific orbital energy
            }
            
            public static void main(String[] args) {
                double v = 7.8e3;     // m/s
                double r = 6.371e6;   // m
                double mu = 3.986e14; // m^3/s^2
                System.out.println("Energy: " + orbitalEnergy(v, r, mu) + " J/kg");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Launch Cost",
            code = """
        public class Main {
            static double costPerKg(double totalCost, double mass) {
                return totalCost / mass;
            }
            
            public static void main(String[] args) {
                double cost = 50e6;  // dollars
                double mass = 1000.0; // kg
                System.out.println("Cost per kg: $" + costPerKg(cost, mass));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Time Dilation",
            code = """
        public class Main {
            static double timeDilation(double t, double v, double c) {
                return t / Math.sqrt(1 - (v * v) / (c * c));
            }
            
            public static void main(String[] args) {
                double t = 1.0;    // seconds
                double v = 0.9e8;  // m/s
                double c = 3.0e8;  // m/s
                System.out.println("Dilated Time: " + timeDilation(t, v, c) + " seconds");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Asteroid Mass",
            code = """
        public class Main {
            static double mass(double radius, double density) {
                return (4.0/3.0) * Math.PI * Math.pow(radius, 3) * density;
            }
            
            public static void main(String[] args) {
                double r = 500.0;    // meters
                double rho = 2000.0; // kg/m^3
                System.out.println("Mass: " + mass(r, rho) + " kg");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Sprite Animation",
            code = """
        public class Main {
            static class Sprite {
                int frame = 0;
                int maxFrames = 4;
                void nextFrame() { frame = (frame + 1) % maxFrames; }
            }
            
            public static void main(String[] args) {
                Sprite sprite = new Sprite();
                sprite.nextFrame();
                System.out.println("Current Frame: " + sprite.frame);
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Input Handler",
            code = """
        public class Main {
            static class Input {
                boolean up, down;
                void process(String key) {
                    if (key.equals("W")) up = true;
                    if (key.equals("S")) down = true;
                }
            }
            
            public static void main(String[] args) {
                Input input = new Input();
                input.process("W");
                System.out.println("Up: " + input.up + ", Down: " + input.down);
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Particle Effect",
            code = """
        import java.util.Random;
        
        public class Main {
            static class Particle {
                double x, y, vx, vy;
                Particle(double x, double y) {
                    Random r = new Random();
                    this.x = x; this.y = y;
                    vx = r.nextDouble() - 0.5;
                    vy = r.nextDouble() - 0.5;
                }
                void update() { x += vx; y += vy; }
            }
            
            public static void main(String[] args) {
                Particle p = new Particle(0, 0);
                p.update();
                System.out.println("Position: (" + p.x + ", " + p.y + ")");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Leaderboard",
            code = """
        import java.util.Arrays;
        
        public class Main {
            static class Leaderboard {
                int[] scores = new int[3];
                void addScore(int score) {
                    scores[0] = score;
                    Arrays.sort(scores);
                }
            }
            
            public static void main(String[] args) {
                Leaderboard lb = new Leaderboard();
                lb.addScore(100);
                System.out.println("Top Score: " + lb.scores[2]);
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Bias Addition",
            code = """
        public class Main {
            static double addBias(double input, double bias) {
                return input + bias;
            }
            
            public static void main(String[] args) {
                double input = 0.5;
                double bias = 0.1;
                System.out.println("Output with Bias: " + addBias(input, bias));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Cross Entropy",
            code = """
        public class Main {
            static double crossEntropy(double[] actual, double[] predicted) {
                double sum = 0;
                for (int i = 0; i < actual.length; i++) {
                    sum -= actual[i] * Math.log(predicted[i]);
                }
                return sum;
            }
            
            public static void main(String[] args) {
                double[] actual = {1, 0};
                double[] predicted = {0.9, 0.1};
                System.out.println("Cross Entropy: " + crossEntropy(actual, predicted));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Dropout Mask",
            code = """
        import java.util.Random;
        
        public class Main {
            static double[] dropout(double[] inputs, double rate) {
                Random rand = new Random();
                double[] mask = new double[inputs.length];
                for (int i = 0; i < inputs.length; i++) {
                    mask[i] = rand.nextDouble() > rate ? inputs[i] / (1 - rate) : 0;
                }
                return mask;
            }
            
            public static void main(String[] args) {
                double[] inputs = {1, 2, 3};
                double[] result = dropout(inputs, 0.2);
                System.out.println("Dropout: [" + result[0] + ", " + result[1] + ", " + result[2] + "]");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Cosine Similarity",
            code = """
        public class Main {
            static double cosineSimilarity(double[] v1, double[] v2) {
                double dot = 0, norm1 = 0, norm2 = 0;
                for (int i = 0; i < v1.length; i++) {
                    dot += v1[i] * v2[i];
                    norm1 += v1[i] * v1[i];
                    norm2 += v2[i] * v2[i];
                }
                return dot / (Math.sqrt(norm1) * Math.sqrt(norm2));
            }
            
            public static void main(String[] args) {
                double[] v1 = {1, 2};
                double[] v2 = {2, 4};
                System.out.println("Similarity: " + cosineSimilarity(v1, v2));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI Batch Normalizer",
            code = """
        public class Main {
            static double[] batchNorm(double[] values) {
                double mean = 0, variance = 0;
                for (double v : values) mean += v;
                mean /= values.length;
                for (double v : values) variance += (v - mean) * (v - mean);
                variance /= values.length;
                double[] norm = new double[values.length];
                for (int i = 0; i < values.length; i++) {
                    norm[i] = (values[i] - mean) / Math.sqrt(variance + 1e-8);
                }
                return norm;
            }
            
            public static void main(String[] args) {
                double[] data = {1, 2, 3};
                double[] norm = batchNorm(data);
                System.out.println("Normalized: [" + norm[0] + ", " + norm[1] + ", " + norm[2] + "]");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "AI KNN Distance",
            code = """
        public class Main {
            static double distance(double[] p1, double[] p2) {
                double sum = 0;
                for (int i = 0; i < p1.length; i++) {
                    sum += (p1[i] - p2[i]) * (p1[i] - p2[i]);
                }
                return Math.sqrt(sum);
            }
            
            public static void main(String[] args) {
                double[] p1 = {1, 2};
                double[] p2 = {3, 4};
                System.out.println("Distance: " + distance(p1, p2));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Session Manager",
            code = """
        import java.util.HashMap;
        
        public class Main {
            static class Session {
                HashMap<String, String> data = new HashMap<>();
                void set(String key, String value) { data.put(key, value); }
                String get(String key) { return data.get(key); }
            }
            
            public static void main(String[] args) {
                Session session = new Session();
                session.set("user", "John");
                System.out.println("User: " + session.get("user"));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Hash Generator",
            code = """
        import java.security.MessageDigest;
        
        public class Main {
            static String md5(String input) throws Exception {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] hash = md.digest(input.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : hash) sb.append(String.format("%02x", b));
                return sb.toString();
            }
            
            public static void main(String[] args) throws Exception {
                System.out.println("Hash: " + md5("password"));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Rate Limiter",
            code = """
        public class Main {
            static class Limiter {
                long lastRequest;
                int period; // ms
                Limiter(int p) { period = p; lastRequest = 0; }
                boolean allow(long now) {
                    if (now - lastRequest >= period) {
                        lastRequest = now;
                        return true;
                    }
                    return false;
                }
            }
            
            public static void main(String[] args) {
                Limiter limiter = new Limiter(1000);
                long now = System.currentTimeMillis();
                System.out.println("Allowed: " + limiter.allow(now));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Query Builder",
            code = """
        public class Main {
            static String buildQuery(String[] keys, String[] values) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < keys.length; i++) {
                    sb.append(keys[i]).append("=").append(values[i]);
                    if (i < keys.length - 1) sb.append("&");
                }
                return sb.toString();
            }
            
            public static void main(String[] args) {
                String[] k = {"name", "age"};
                String[] v = {"John", "30"};
                System.out.println("Query: " + buildQuery(k, v));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Latency Simulator",
            code = """
        public class Main {
            static double latency(double distance, double speed) {
                return distance / speed * 1000; // km, km/s -> ms
            }
            
            public static void main(String[] args) {
                double dist = 100.0;  // km
                double speed = 200000.0; // km/s
                System.out.println("Latency: " + latency(dist, speed) + " ms");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Web Token Generator",
            code = """
        import java.util.Random;
        
        public class Main {
            static String generateToken(int length) {
                Random rand = new Random();
                StringBuilder sb = new StringBuilder();
                String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
                for (int i = 0; i < length; i++) {
                    sb.append(chars.charAt(rand.nextInt(chars.length())));
                }
                return sb.toString();
            }
            
            public static void main(String[] args) {
                System.out.println("Token: " + generateToken(8));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Series Capacitance",
            code = """
        public class Main {
            static double series(double c1, double c2) {
                return 1 / (1/c1 + 1/c2); // farads
            }
            
            public static void main(String[] args) {
                double c1 = 0.0001; // farads
                double c2 = 0.0002; // farads
                System.out.println("Series Capacitance: " + series(c1, c2) + " F");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Impedance",
            code = """
        public class Main {
            static double impedance(double r, double xl) {
                return Math.sqrt(r * r + xl * xl);
            }
            
            public static void main(String[] args) {
                double r = 1000.0;  // ohms
                double xl = 500.0;  // ohms (inductive reactance)
                System.out.println("Impedance: " + impedance(r, xl) + " ohms");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Amp Gain",
            code = """
        public class Main {
            static double gain(double rf, double rin) {
                return -rf / rin; // Inverting amplifier gain
            }
            
            public static void main(String[] args) {
                double rf = 10000.0; // ohms
                double rin = 1000.0; // ohms
                System.out.println("Gain: " + gain(rf, rin));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Noise Power",
            code = """
        public class Main {
            static double noisePower(double bandwidth, double temp) {
                double k = 1.38e-23; // Boltzmann constant
                return k * temp * bandwidth; // watts
            }
            
            public static void main(String[] args) {
                double bw = 1e6;  // Hz
                double t = 300.0; // Kelvin
                System.out.println("Noise Power: " + noisePower(bw, t) + " W");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Transformer Ratio",
            code = """
        public class Main {
            static double turnsRatio(double vp, double vs) {
                return vp / vs;
            }
            
            public static void main(String[] args) {
                double vp = 240.0; // volts (primary)
                double vs = 12.0;  // volts (secondary)
                System.out.println("Turns Ratio: " + turnsRatio(vp, vs));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Electronics Phase Shift",
            code = """
        public class Main {
            static double phaseShift(double r, double c, double f) {
                return Math.atan(1 / (2 * Math.PI * f * r * c)) * 180 / Math.PI;
            }
            
            public static void main(String[] args) {
                double r = 1000.0;  // ohms
                double c = 0.000001; // farads
                double f = 1000.0;  // Hz
                System.out.println("Phase Shift: " + phaseShift(r, c, f) + " degrees");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Burn Time",
            code = """
        public class Main {
            static double burnTime(double thrust, double massFlow, double deltaV) {
                return (massFlow * deltaV) / thrust;
            }
            
            public static void main(String[] args) {
                double thrust = 10000.0; // N
                double mdot = 5.0;       // kg/s
                double dv = 1000.0;      // m/s
                System.out.println("Burn Time: " + burnTime(thrust, mdot, dv) + " s");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Periapsis",
            code = """
        public class Main {
            static double periapsis(double a, double e) {
                return a * (1 - e);
            }
            
            public static void main(String[] args) {
                double a = 7.0e6;  // semi-major axis m
                double e = 0.1;    // eccentricity
                System.out.println("Periapsis: " + periapsis(a, e) + " m");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Inclination Change",
            code = """
        public class Main {
            static double deltaV(double v, double angle) {
                return 2 * v * Math.sin(angle / 2);
            }
            
            public static void main(String[] args) {
                double v = 7.8e3;        // m/s
                double angle = Math.PI / 6; // 30 degrees
                System.out.println("Delta-V: " + deltaV(v, angle) + " m/s");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Doppler Shift",
            code = """
        public class Main {
            static double doppler(double f0, double v, double c) {
                return f0 * (c / (c - v));
            }
            
            public static void main(String[] args) {
                double f0 = 1e9;   // Hz
                double v = 1000.0; // m/s
                double c = 3.0e8;  // m/s
                System.out.println("Shifted Frequency: " + doppler(f0, v, c) + " Hz");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Radiation Pressure",
            code = """
        public class Main {
            static double pressure(double power, double r) {
                double c = 3.0e8;
                return power / (4 * Math.PI * r * r * c);
            }
            
            public static void main(String[] args) {
                double p = 3.828e26; // watts (Sun)
                double r = 1.496e11; // meters
                System.out.println("Pressure: " + pressure(p, r) + " N/m^2");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Space Orbital Decay",
            code = """
        public class Main {
            static double decayRate(double drag, double mass, double v) {
                return drag / (mass * v);
            }
            
            public static void main(String[] args) {
                double drag = 0.1;   // N
                double m = 1000.0;   // kg
                double v = 7.8e3;    // m/s
                System.out.println("Decay Rate: " + decayRate(drag, m, v) + " m/s");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Fog of War",
            code = """
        public class Main {
            static class Map {
                boolean[][] visible = new boolean[3][3];
                void reveal(int x, int y) { visible[x][y] = true; }
                boolean isVisible(int x, int y) { return visible[x][y]; }
            }
            
            public static void main(String[] args) {
                Map map = new Map();
                map.reveal(1, 1);
                System.out.println("Visible at (1,1): " + map.isVisible(1, 1));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Damage Multiplier",
            code = """
        public class Main {
            static double calculateDamage(double base, double multiplier) {
                return base * multiplier;
            }
            
            public static void main(String[] args) {
                double base = 50.0;
                double mult = 1.5;
                System.out.println("Damage: " + calculateDamage(base, mult));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Path Cost",
            code = """
        public class Main {
            static int pathCost(int[][] grid, int x, int y) {
                return grid[x][y];
            }
            
            public static void main(String[] args) {
                int[][] grid = {{1, 2}, {3, 4}};
                System.out.println("Cost at (1,1): " + pathCost(grid, 1, 1));
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Buff Timer",
            code = """
        public class Main {
            static class Buff {
                long start;
                int duration; // ms
                Buff(int d) { duration = d; start = System.currentTimeMillis(); }
                boolean isActive() { return System.currentTimeMillis() - start < duration; }
            }
            
            public static void main(String[] args) throws InterruptedException {
                Buff buff = new Buff(2000);
                Thread.sleep(1000);
                System.out.println("Buff Active: " + buff.isActive());
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Camera Follow",
            code = """
        public class Main {
            static class Camera {
                double x, y;
                void follow(double px, double py) { x = px; y = py; }
            }
            
            public static void main(String[] args) {
                Camera cam = new Camera();
                cam.follow(10.0, 20.0);
                System.out.println("Camera at: (" + cam.x + ", " + cam.y + ")");
            }
        }
    """.trimIndent()
        ),
        Example(
            name = "Game Experience Points",
            code = """
        public class Main {
            static class Player {
                int xp = 0;
                int level = 1;
                void gainXP(int amount) {
                    xp += amount;
                    if (xp >= 100) { level++; xp -= 100; }
                }
            }
            
            public static void main(String[] args) {
                Player p = new Player();
                p.gainXP(150);
                System.out.println("Level: " + p.level + ", XP: " + p.xp);
            }
        }
    """.trimIndent()
        )
    )
}