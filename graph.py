extra_visits = 5

class Graph:
	def __init__(self):
		self.nodes = {}
		self.node_value = 0
		self.longest_simple_cycle_length = 0
		self.longest_simple_cycle_path = []

	def add_node(self):
		self.node_value += 1
		if self.node_value not in self.nodes:
			self.nodes[self.node_value] = Node(self.node_value)
			return self.node_value

	def get_node(self, node_value):
		return self.nodes[node_value]

	def add_edge(self, parent_value, child_value):
		parent_object = self.nodes[parent_value]
		child_object = self.nodes[child_value]
		return parent_object.add_edge(child_object)

	def print_nodes(self):
		for node_key in self.nodes:
			print node_key

	def find_longest_simple_cycle(self):
		self.longest_simple_cycle_length = 0
		self.longest_simple_cycle_path = []

		for node_object in self.nodes.itervalues():
			node_object.unvisit()

		for node_object in self.nodes.itervalues():
			if node_object.status == 0:
				self.dfs(node_object)

		return self.longest_simple_cycle_length

	def dfs(self, u):
		u.visit()

		for v in u.adjacency_list.itervalues():
			if v.status == 0 or v.should_visit():
				v.parent = u
				self.dfs(v)
			elif v.status == 1:
				temp = u # temporary variable used to traverse parents
				cycle_length = 1

				# the cycles found may be duplicates
				print "--- Cycle found ---"
				cycle_path = [temp.value]

				while temp is not v:
					cycle_length += 1
					temp = temp.parent
					cycle_path.append(temp.value)

				print cycle_path

				if self.longest_simple_cycle_length < cycle_length:
					self.longest_simple_cycle_length = cycle_length
					self.longest_simple_cycle_path = cycle_path

		u.complete()

class Node:
	def __init__(self, value):
		self.value = value
		self.parent = None
		self.status = 0 # 0 = unvisited, 1 = visited, 2 = complete
		self.incoming_edges = 0
		self.times_visited = 0
		self.adjacency_list = {}

	def add_edge(self, child):
		if child.value not in self.adjacency_list:
			self.adjacency_list[child.value] = child
			child.incoming_edges += 1
			return True

	def unvisit(self):
		self.status = 0
		self.times_visited = 0

	def visit(self): 
		self.status = 1
		self.times_visited += 1

	def complete(self):
		self.status = 2

	def should_visit(self):
		return self.times_visited < self.incoming_edges + extra_visits and self.status == 2

	def print_adjacency_list(self):
		for node_key in self.adjacency_list:
			print node_key

graph = Graph()
for x in xrange(0, 10): # create nodes 1 through 10
	graph.add_node()

graph.add_edge(1, 2)
graph.add_edge(1, 3)
graph.add_edge(2, 4)
graph.add_edge(2, 5)
graph.add_edge(3, 7)
graph.add_edge(4, 1)
graph.add_edge(4, 2)
graph.add_edge(5, 4)
graph.add_edge(6, 3)
graph.add_edge(6, 5)
graph.add_edge(7, 3)
graph.add_edge(7, 6)

print "Printing all nodes in graph"
graph.print_nodes()
#print "Printing adjacency list for node 1"
#graph.get_node(1).print_adjacency_list()
print "Calculating longest simple cycle length"
graph.find_longest_simple_cycle()
print "Printing longest simple cycle length"
print graph.longest_simple_cycle_length
print "Printing a longest simple cycle path"
print graph.longest_simple_cycle_path