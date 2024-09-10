    import java.util.*;

    class set_cover {
        static class SetInfo {
            Set<Integer> elements;
            int cost;
            String name;

            SetInfo(Set<Integer> elements, int cost, String name) {
                this.elements = elements;
                this.cost = cost;
                this.name = name;
            }
        }

        public static int findMinIndex(List<Double> list) {
            int minIndex = 0;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) < list.get(minIndex)) {
                    minIndex = i;
                }
            }
            return minIndex;
        }

        public static void solve() {
            double minCost = 0;
            Set<Integer> universe = new HashSet<>(Arrays.asList(12, 13, 12, 14, 15));
            List<SetInfo> sets = new ArrayList<>();
            sets.add(new SetInfo(new HashSet<>(Arrays.asList(11, 13, 14)), 15, "S1"));
            sets.add(new SetInfo(new HashSet<>(Arrays.asList(12, 15)), 20, "S2"));
            sets.add(new SetInfo(new HashSet<>(Arrays.asList(11, 13, 12, 14)), 30, "S3"));

            List<String> output = new ArrayList<>();

            System.out.println("U=" + universe);
            for (SetInfo set : sets) {
                System.out.println(set.name + ":" + set.elements + ", Cost=" + set.cost);
            }

            while (!universe.isEmpty()) {
                List<Double> alphas = new ArrayList<>();
                for (SetInfo set : sets) {
                    double alpha = (double) set.cost / set.elements.size();
                    alphas.add(alpha);
                }

                int minIndex = findMinIndex(alphas);
                minCost += alphas.get(minIndex) * sets.get(minIndex).elements.size();
                output.add(sets.get(minIndex).name);
                Set<Integer> cover = sets.get(minIndex).elements;
                universe.removeAll(cover);
                sets.remove(minIndex);

                for (SetInfo set : sets) {
                    set.elements.retainAll(universe);
                }
            }

            System.out.println("Set cover is");
            System.out.println(output + ", Cost=" + minCost);
        }

        public static void main(String[] args) {
            solve();
        }
    }
